package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.entities.carrier;
import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.service.ICarrierService;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/places")
@Api(tags = "Places", value = "Web Service RESTFul of Places")
public class PlacesControlller {
    private final IPlacesService placesService;
    private final ICarrierService bussinessService;

    public PlacesControlller(IPlacesService placesService, ICarrierService bussinessService) {
        this.placesService = placesService;
        this.bussinessService = bussinessService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Places", notes = "Method to list all Places")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All places founds"),
            @ApiResponse(code = 404, message = "Places Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Places>> findAllplaces(){
        try {
            List<Places> places = placesService.getAll();
            if(places.size()>0)
                return new ResponseEntity<>(places, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search places by Id", notes = "Method for find a places by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Places found by Id"),
            @ApiResponse(code = 404, message = "Places Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Places> findById (@PathVariable("id") Long id){
        try {
            Optional<Places> places = placesService.getById(id);
            if(places == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(places.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create places", notes = "Method for create a places")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Places created"),
            @ApiResponse(code = 404, message = "Places Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Places> createplaces(@PathVariable("id") Long id, @RequestBody Places places){
        try {
            Optional<carrier> userBussiness = bussinessService.getById(id);
            if(!userBussiness.isPresent() )
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                places.setCarriers(userBussiness.get());
                Places newplaces = placesService.save(places);
                return ResponseEntity.status(HttpStatus.CREATED).body(newplaces);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update places", notes = "Method for update a places")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Places updated"),
            @ApiResponse(code = 404, message = "Places Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Places> updateplaces(@PathVariable("id") Long id, @Valid @RequestBody Places places){
        try {
            Optional<Places> placesFound = placesService.getById(id);
            if(!placesFound.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                places.setId(id);
                placesService.save(places);
                return new ResponseEntity<>(places, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






    @GetMapping(value = "/destino/{destino}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search places by destino", notes = "Method for find a places by destino")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Places found by destino"),
            @ApiResponse(code = 404, message = "Places Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Places>> findByDestino (@PathVariable("destino") String destino){
        try {
            List<Places> places = placesService.findByDestino(destino);
            if(places.size()>0)
                return new ResponseEntity<>(places, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete places", notes = "Method for delete a places")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Places deleted"),
            @ApiResponse(code = 404, message = "Places Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Places> deleteplaces(@PathVariable("id") Long id){
        try {
            Optional<Places> placesFound = placesService.getById(id);
            if(!placesFound.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                placesService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/bussiness/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search places by Bussiness Id", notes = "Method for find a places by Bussiness")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Places found by Bussiness Id"),
            @ApiResponse(code = 404, message = "Places Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Places>> findByBussinessId (@PathVariable("id") Long id){
        try {
            Optional<carrier> places =bussinessService.getById(id);

            if(!places.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else{
                List<Places> place = placesService.findByBussiness_Id(id);
                return new ResponseEntity<>(place, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
