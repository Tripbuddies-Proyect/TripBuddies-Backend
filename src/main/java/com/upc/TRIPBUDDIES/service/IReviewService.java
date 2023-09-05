package com.upc.TRIPBUDDIES.service;

import com.upc.TRIPBUDDIES.entities.Review;

import java.util.List;

public interface IReviewService extends CrudService<Review>{
    List<Review> findByPlaces_Id(Long places_id) throws Exception;
    List<Review> findReviewByTravellerId(Long traveller_id) throws Exception;
}
