package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.entities.Favorite;
import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.entities.Traveller;
import com.upc.TRIPBUDDIES.repository.IFavoriteRepository;
import com.upc.TRIPBUDDIES.service.IPlacesService;
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
@RequestMapping("/api/v1/favorites")
@Api(tags = "Favorite", value = "Web Service RESTFul of Favorite")
public class FavoriteController {

    private final IFavoriteRepository favoriteRepository;
    private final IPlacesService placesService;
    private final ITravellerService travellerService;

    @Autowired
    public FavoriteController(IFavoriteRepository favoriteRepository, IPlacesService placesService, ITravellerService travellerService) {
        this.favoriteRepository = favoriteRepository;
        this.placesService = placesService;
        this.travellerService = travellerService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Favorites", notes = "Method to list all Favorites")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Favorites founds"),
            @ApiResponse(code = 404, message = "Favorites Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Favorite>> findAllFavorites(){
        try {
            List<Favorite> favorites = favoriteRepository.findAll();
            if (favorites.size() > 0){
                return new ResponseEntity<>(favorites, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "travellerId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Favorites by Traveller", notes = "Method to list all Favorites by Traveller")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Favorites founds"),
            @ApiResponse(code = 404, message = "Favorites Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Favorite>> findAllFavoritesByTraveller(@PathVariable("id") Long id){
        try {
            Optional<Traveller> traveller = travellerService.getById(id);
            if (!traveller.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<Favorite> favorites = favoriteRepository.findByTravellerId(id);
                return new ResponseEntity<>(favorites, HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/{userId}/{placeId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Favorite", notes = "Method to save Favorite")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Favorite found"),
            @ApiResponse(code = 404, message = "Favorite Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Favorite> insertFavorite(@PathVariable("userId") Long userId,@PathVariable("placeId") Long placeId, @RequestBody Favorite favorite) {
        try {
            Optional<Traveller> traveller = travellerService.getById(userId);
            Optional<Places> places = placesService.getById(placeId);

            if (traveller.isPresent() && places.isPresent()) {
                favorite.setTraveller(traveller.get());
                favorite.setPlaces(places.get());
                Favorite favoriteNew = favoriteRepository.save(favorite);
                return ResponseEntity.status(HttpStatus.CREATED).body(favoriteNew);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Favorite", notes = "Method to delete Favorite")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Favorite found"),
            @ApiResponse(code = 404, message = "Favorite Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Favorite> deleteFavorite(@PathVariable("id") Long id){
        try {
            Optional<Favorite> favoriteDelete = favoriteRepository.findById(id);
            if (favoriteDelete.isPresent()){
                favoriteRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
