package com.upc.TRIPBUDDIES.controller;


import com.upc.TRIPBUDDIES.entities.Adquisicions;
import com.upc.TRIPBUDDIES.entities.carrier;
import com.upc.TRIPBUDDIES.service.IAdquisicionsService;
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
@RequestMapping("/api/v1/Adquisicions")
@Api(tags = "Adquisicions", value = "Web Service RESTFul of Adquisicions")
public class AdquisicionsController {

    private final IAdquisicionsService adquisicionsService;
    public AdquisicionsController(IAdquisicionsService adquisicionsService) {
        this.adquisicionsService = adquisicionsService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Adquisicions", notes = "Method to list all Adquisicions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Adquisicions founds"),
            @ApiResponse(code = 404, message = "Adquisicions Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Adquisicions>> findAllAdquisicions(){
        try {
            List<Adquisicions> bussinesses = adquisicionsService.getAll();
            if(bussinesses.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(bussinesses, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Adquisicions by Id", notes = "Method for find a Adquisicions by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Adquisicions found by Id"),
            @ApiResponse(code = 404, message = "Adquisicions Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Adquisicions> findAdquisicionsById(@PathVariable("id") Long id){
        try {
            Optional<Adquisicions> bussiness = adquisicionsService.getById(id);
            return bussiness.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Adquisicions", notes = "Method for create a Adquisicions")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Adquisicions created"),
            @ApiResponse(code = 400, message = "Adquisicions Bad Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Adquisicions> createBussiness(@Valid @RequestBody Adquisicions carrier){
        try {
            Adquisicions bussinessCreate = adquisicionsService.save(carrier);
            return new ResponseEntity<>(bussinessCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Adquisicions", notes = "Method for delete a Adquisicions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Adquisicions deleted"),
            @ApiResponse(code = 404, message = "Adquisicions Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Adquisicions> deleteAdquisicions(@PathVariable("id") Long id){
        try {
            Optional<Adquisicions> traveller = adquisicionsService.getById(id);
            if(traveller.isPresent()){
                adquisicionsService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
