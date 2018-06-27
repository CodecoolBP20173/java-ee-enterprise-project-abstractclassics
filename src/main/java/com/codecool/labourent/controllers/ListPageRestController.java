package com.codecool.labourent.controllers;

import com.codecool.labourent.model.Rating;
import com.codecool.labourent.service.RatingService;
import com.codecool.labourent.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
public class ListPageRestController {

    @Autowired
    RatingService ratingService;

    @Autowired
    ServiceService serviceService;

    @RequestMapping(value = "/list/rating", method = RequestMethod.POST)
    public String getRating(@RequestParam("rating") Integer rating,
                            @RequestParam("serviceId") Integer serviceId,
                            HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (!ratingService.isRatingRelationPresent(userId, serviceId)) {
            ratingService.saveRatingRelation(userId, serviceId);
            serviceService.increaseRatingStats(rating, serviceId);
        }
        return "success";
    }
}
