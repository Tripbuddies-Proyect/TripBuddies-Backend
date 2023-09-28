package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.entities.Review;
import com.upc.TRIPBUDDIES.entities.Traveller;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import com.upc.TRIPBUDDIES.service.IReviewService;
import com.upc.TRIPBUDDIES.service.ITravellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private IReviewService reviewService;

    @Mock
    private IPlacesService placesService;

    @Mock
    private ITravellerService travellerService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }



    // Paso 4: Verificar que la creación de Review sea exitosa
    @Test
    void testTouristCanLeaveReviewAfterTwoDays() throws Exception {
        // Paso 1: Crear un turista  en el sistema
        Traveller user = new Traveller();
        user.setId(1L);
        // Paso 2: Crear una ruta turística  con una fecha de viaje específica
        Places places = new Places();
        places.setId(2L);
        places.setDate("25/09/2024"); // Fecha de viaje,
        // Paso 3:  turista comente un post
        Review reviewToCreate = new Review();
        reviewToCreate.setReviewText("Nueva reseña");
        reviewToCreate.setPlaces(places);
        //  respuestas de los servicios
        when(travellerService.getById(1L)).thenReturn(Optional.of(user));
        when(placesService.getById(2L)).thenReturn(Optional.of(places));
        when(reviewService.save(reviewToCreate)).thenReturn(reviewToCreate);
        // Simular la fecha actual como 27/09/2024, lo que significa que han pasado más de dos días desde el viaje
        LocalDate currentDate = LocalDate.of(2024, 9, 27);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        // Realizar la solicitud POST para crear Review
        ResponseEntity<Review> responseReview = reviewController.insertReview(2L, reviewToCreate);
        // Verificar que la creación de Review fue exitosa
        assertEquals(HttpStatus.CREATED, responseReview.getStatusCode());
    }

    // Test para el caso de que el turista no pueda dejar una reseña antes de dos días
    @Test
    void testTouristCannotLeaveReviewBeforeTwoDays() throws Exception {
        // Paso 1: Crear un turista  en el sistema
        Traveller user = new Traveller();
        user.setId(1L);
        // Paso 2: Crear una ruta turística con una fecha de viaje específica
        Places places = new Places();
        places.setId(2L);
        places.setDate("27/09/2024"); // Fecha de viaje,
        // Paso 3:  turista comente un post
        Review reviewToCreate = new Review();
        reviewToCreate.setReviewText("Nueva reseña");
        reviewToCreate.setPlaces(places);
        // respuestas de los servicios
        when(travellerService.getById(1L)).thenReturn(Optional.of(user));
        when(placesService.getById(2L)).thenReturn(Optional.of(places));
        // Simular la fecha actual como 26/09/2024, lo que significa que no han pasado dos o uno días desde el viaje
        LocalDate currentDate = LocalDate.of(2024, 9, 26);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        // Realizar la solicitud POST simulada para crear Review
        ResponseEntity<Review> responseReview = reviewController.insertReview(2L, reviewToCreate);
        // Verificar que la creación de Review no sea exitosa debido a que no han pasado dos días desde el viaje
        assertEquals(HttpStatus.FAILED_DEPENDENCY, responseReview.getStatusCode());
    }



}
