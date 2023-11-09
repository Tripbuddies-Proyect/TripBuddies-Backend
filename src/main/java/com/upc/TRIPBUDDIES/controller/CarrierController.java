package com.upc.TRIPBUDDIES.controller;


import com.upc.TRIPBUDDIES.entities.carrier;
import com.upc.TRIPBUDDIES.service.ICarrierService;
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
@RequestMapping("/api/v1/Carrier")
@Api(tags = "Carriers", value = "Web Service RESTFul of Carriers")
public class CarrierController {
    private final ICarrierService carrierService;

    public CarrierController(ICarrierService carrierService) {
        this.carrierService = carrierService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Bussiness", notes = "Method to list all Bussiness")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Bussinesss founds"),
            @ApiResponse(code = 404, message = "Bussinesss Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<carrier>> findAllBussiness(){
        try {
            List<carrier> bussinesses = carrierService.getAll();
            if(bussinesses.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(bussinesses, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Bussiness by Id", notes = "Method for find a Bussiness by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Bussiness found by Id"),
            @ApiResponse(code = 404, message = "Bussiness Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<carrier> findBussinessById(@PathVariable("id") Long id){
        try {
            Optional<carrier> bussiness = carrierService.getById(id);
            return bussiness.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Bussiness", notes = "Method for create a Bussiness")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Bussiness created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<carrier> createBussiness(@Valid @RequestBody carrier carrier){
        try {
            carrier bussinessCreate = carrierService.save(carrier);
            return new ResponseEntity<>(bussinessCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Bussiness", notes = "Method for update a Bussiness")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Bussiness updated"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<carrier> updateBussiness(@PathVariable("id") Long id, @Valid @RequestBody carrier carrier){
        try {
            if(id.equals(carrier.getId())){
                carrier carrierUpdate = carrierService.save(carrier);
                return new ResponseEntity<>(carrierUpdate, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Bussiness", notes = "Method for delete a Bussiness")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Bussiness deleted"),
            @ApiResponse(code = 404, message = "Bussiness Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<carrier> deleteTraveller(@PathVariable("id") Long id){
        try {
            Optional<carrier> traveller = carrierService.getById(id);
            if(traveller.isPresent()){
                carrierService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/email/{email}/password/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Carrier by email and password", notes = "Method for get a Carrier")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Carrier Get"),
            @ApiResponse(code = 404, message = "Carrier Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<carrier> getCarrierByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password){
        try {
            carrier carrier = carrierService.existsByEmailAndPassword(email, password);
            if(carrier != null){
                return new ResponseEntity<>(carrier, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
