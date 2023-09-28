package com.upc.TRIPBUDDIES.controller;

import com.sun.tools.jconsole.JConsoleContext;
import com.upc.TRIPBUDDIES.entities.Adquisicions;
import com.upc.TRIPBUDDIES.entities.Payment;
import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.entities.carrier;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.upc.TRIPBUDDIES.repository.IPlacesRepository;
import com.upc.TRIPBUDDIES.service.ICarrierService;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import com.upc.TRIPBUDDIES.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class PaymentControllerTest {
    @InjectMocks
    private PaymentController paymentController;
    @Mock
    private IPlacesRepository placesRepository;
    @Mock
    private PaymentServiceImpl saleTableService;
    @Mock
    private IPlacesService PlacesService;
    @Mock
    private ICarrierService CarrierService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPayment_ValidRequest() throws Exception{
        Long userId = 1L;
        Long placeId = 2L;
        Payment payment = new Payment();
        payment.setId(3L);
        payment.setAmount(100);
        payment.setDate("28/09/2023");
        carrier carrier = new carrier();
        carrier.setId(userId);
        carrier.setRuc("123456789");
        carrier.setCity("Lima");
        carrier.setAddress("Av. Los Olivos");
        carrier.setPhone("987654321");
        carrier.setPlate("ABC-211");
        carrier.setMarca("Toyota");
        Places place = new Places();
        place.setId(placeId);
        place.setOrigen("Lima");
        place.setCarriers(carrier);
        Mockito.when(placesRepository.existsById(placeId)).thenReturn(true);
        Mockito.when(CarrierService.getById(userId)).thenReturn(Optional.of(carrier));
        Mockito.when(PlacesService.getById(placeId)).thenReturn(Optional.of(place));
        Mockito.when(saleTableService.save(any(Payment.class))).thenReturn(payment);
        Payment requestPayment = new Payment();
        ResponseEntity<Payment> response = paymentController.createPayment(carrier.getId(), place.getId(), requestPayment);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(payment, response.getBody());

    }

}
