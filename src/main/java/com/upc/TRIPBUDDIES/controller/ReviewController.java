package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.entities.Review;
import com.upc.TRIPBUDDIES.entities.Traveller;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import com.upc.TRIPBUDDIES.service.IReviewService;
import com.upc.TRIPBUDDIES.service.ITravellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@Api(value = "Web Service RESTFul of Reviews", tags = "Reviews")
public class ReviewController {

    private final IReviewService reviewService;

    private final IPlacesService placesService;

    private final ITravellerService travellerService;

    public ReviewController(IReviewService reviewService, IPlacesService placesService, ITravellerService travellerService) {
        this.reviewService = reviewService;
        this.placesService = placesService;
        this.travellerService = travellerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Reviews", notes = "Method to list all Reviews")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Reviews founds"),
            @ApiResponse(code = 404, message = "Reviews Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Review>> findAllReviews(){
        try {
            List<Review> reviews = reviewService.getAll();
            if (reviews.size() > 0){
                return new ResponseEntity<>(reviews, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Review by Id", notes = "Method for finding an Review by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Review found by Id"),
            @ApiResponse(code = 404, message = "Review Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Review> findReviewById(@PathVariable("id") Long id){
        try {
            Optional<Review> review = reviewService.getById(id);
            if (!review.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(review.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "places/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Review", notes = "Method to create an Review")
    @ApiResponses( {
            @ApiResponse(code = 201, message = "Review found"),
            @ApiResponse(code = 404, message = "Review not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Review> insertReview(@PathVariable("id") Long id, @RequestBody Review review){
        try {
            Optional<Places> reviewNew = placesService.getById(id);
            if (!reviewNew.isPresent()){
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
            }else {
                review.setPlaces(reviewNew.get());
                Review reviewCreate = reviewService.save(review);
                return ResponseEntity.status(HttpStatus.CREATED).body(reviewCreate);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/traveller/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Review by Traveller Id", notes = "Method for finding an Review by Traveller id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Review found by Traveller Id"),
            @ApiResponse(code = 404, message = "Review Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Review>> findReviewByTravellerId(@PathVariable("id") Long id){
        try {
            Optional<Traveller> traveller = travellerService.getById(id);
            if (!traveller.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                List<Review> reviews = reviewService.findReviewByTravellerId(id);
                return new ResponseEntity(reviews, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/places/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Review by Places Id", notes = "Method for finding an Review by Places id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Review found by Places Id"),
            @ApiResponse(code = 404, message = "Review Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Review>> findReviewByPlacesId(@PathVariable("id") Long id){
        try {
            Optional<Places> places = placesService.getById(id);
            if (!places.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                List<Review> reviews = reviewService.findByPlaces_Id(id);
                return new ResponseEntity(reviews, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Reviews data", notes = "Method to update Reviews")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Review updated"),
            @ApiResponse(code = 404, message = "Review not updated"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Review> updateReview(@PathVariable("id") Long id, @RequestBody Review review){
        try {
            Optional<Review> reviewUpdate = reviewService.getById(id);
            if (!reviewUpdate.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else{
                review.setId(id);
                reviewService.save(review);
                return new ResponseEntity<>(review, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Review", notes = "Method to delete Review")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Review deleted"),
            @ApiResponse(code = 404, message = "Review not deleted"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Review> deleteReview(@PathVariable("id") Long id) {
        try {
            Optional<Review> reviewDelete = reviewService.getById(id);
            if (!reviewDelete.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                reviewService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
