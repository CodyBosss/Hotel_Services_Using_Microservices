package com.sk.user.service.external.services;

import com.sk.user.service.entities.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    //get

    //post
    @PostMapping("/ratings")
    public ResponseEntity<Ratings> createRating(Ratings values);

    //put
    @PutMapping("/ratings/{ratingId}")
    public ResponseEntity<Ratings> UpdateRating(@PathVariable("ratingId") String ratingId, Ratings ratings);

    //delete

    @DeleteMapping("/ratings/{ratingId}")
    public  void deleteRating(@PathVariable String ratingId);
}
