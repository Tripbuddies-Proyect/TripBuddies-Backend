package com.upc.TRIPBUDDIES.repository;

import com.upc.TRIPBUDDIES.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlaces_Id(Long places_id);
    List<Review> findReviewByTravellerId(Long traveller_id) throws Exception;

}
