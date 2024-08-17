package com.sk.user.service.services.impl;

import com.sk.user.service.entities.Hotel;
import com.sk.user.service.entities.Ratings;
import com.sk.user.service.entities.User;
import com.sk.user.service.exceptions.ResourceNotFoundException;
import com.sk.user.service.external.services.HotelService;
import com.sk.user.service.repositories.UserRepository;
import com.sk.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    // get single user
    @Override
    public User getUser(String userId) {
       // get user from database with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found on server !! : " + userId));
       //fetch rating of the above user from rating service
        //http://localhost:9093/ratings/users/c3a7eb64-c064-4e65-9839-4318414ff6c9

        Ratings[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Ratings[].class);
        logger.info("{} ", ratingsOfUser);

        List<Ratings>ratings = Arrays.stream(ratingsOfUser).toList();

        List<Ratings> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
     //localhost:9092/hotels/473b0b93-61fb-44cb-b652-cbd56a77c616
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            logger.info("response status code : {} ",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

}

