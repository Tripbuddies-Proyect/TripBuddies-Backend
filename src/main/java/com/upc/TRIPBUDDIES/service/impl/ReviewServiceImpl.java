package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.Review;
import com.upc.TRIPBUDDIES.repository.IReviewRepository;
import com.upc.TRIPBUDDIES.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements IReviewService {

    private final IReviewRepository reviewRepository;
    public ReviewServiceImpl(IReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    @Transactional
    public Review save(Review review) throws Exception {
        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Review> getAll() throws Exception {
        return reviewRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Review> getById(Long id) throws Exception {
        return reviewRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Review> findByPlaces_Id(Long places_id) throws Exception {
        return reviewRepository.findByPlaces_Id(places_id);
    }

    @Override
    @Transactional
    public List<Review> findReviewByTravellerId(Long traveller_id) throws Exception {
        return reviewRepository.findReviewByTravellerId(traveller_id);
    }
}
