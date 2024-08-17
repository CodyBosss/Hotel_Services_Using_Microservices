package com.sk.user.service;

import com.sk.user.service.entities.Ratings;
import com.sk.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

//	@Test
//	void createRating(){
//		Ratings rating = Ratings.builder().rating(10).userId("").hotelId("").feedback("this is created by feign client").build();
//		ResponseEntity<Ratings> ratingResponseEntity = ratingService.createRating(rating);
//		ratingResponseEntity.getStatusCode();
//		System.out.println("new rating created");
//	}

}
