package com.sk.rating.services;

import com.sk.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRating();

    //get all by userId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);

}
