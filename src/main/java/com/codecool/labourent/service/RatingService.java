package com.codecool.labourent.service;

import com.codecool.labourent.model.Rating;
import com.codecool.labourent.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;



    public boolean isRatingRelationPresent(Integer userId, Integer serviceId) {
        List<Rating> ratings = ratingRepository.findByUseraccount(userId, serviceId);
        if(ratings == null) {
            return false;
        }
        return true;
    }

    public void saveRatingRelation(Integer userId, Integer serviceId) {
        ratingRepository.saveRating(userId, serviceId);
    }
}
