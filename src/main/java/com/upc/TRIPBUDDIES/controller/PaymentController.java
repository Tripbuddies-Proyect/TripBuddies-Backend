package com.upc.TRIPBUDDIES.controller;


import com.upc.TRIPBUDDIES.entities.Adquisicions;
import com.upc.TRIPBUDDIES.entities.Payment;
import com.upc.TRIPBUDDIES.service.IAdquisicionsService;
import com.upc.TRIPBUDDIES.service.IPaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Payment")
@Api(tags = "Payment", value = "Web Service RESTFul of Payment")
public class PaymentController {
    @Autowired
    private final IPaymentService paymentService;


    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Payments", notes = "Method to list all Payments")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Payments founds"),
            @ApiResponse(code = 404, message = "Payments Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Payment>> findAllPayments(){
        try {
            List<Payment> bussinesses = paymentService.getAll();
            if(bussinesses.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(bussinesses, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Payments by Id", notes = "Method for find a Payments by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Payments found by Id"),
            @ApiResponse(code = 404, message = "Payments Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Payment> findPaymentsById(@PathVariable("id") Long id){
        try {
            Optional<Payment> bussiness = paymentService.getById(id);
            return bussiness.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create payments", notes = "Method for create a payments")
    @ApiResponses({
            @ApiResponse(code = 201, message = "payments created"),
            @ApiResponse(code = 400, message = "payments Bad Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Payment> createBussiness(@Valid @RequestBody Payment carrier){
        try {
            Payment bussinessCreate = paymentService.save(carrier);
            return new ResponseEntity<>(bussinessCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Payments", notes = "Method for delete a Payments")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Payments deleted"),
            @ApiResponse(code = 404, message = "Payments Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Payment> deletePayments(@PathVariable("id") Long id){
        try {
            Optional<Payment> traveller = paymentService.getById(id);
            if(traveller.isPresent()){
                paymentService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
