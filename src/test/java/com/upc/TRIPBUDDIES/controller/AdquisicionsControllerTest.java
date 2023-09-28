package com.upc.TRIPBUDDIES.controller;


import com.upc.TRIPBUDDIES.entities.Adquisicions;
import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.entities.Traveller;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import com.upc.TRIPBUDDIES.service.ITravellerService;
import com.upc.TRIPBUDDIES.service.impl.AdquisicionsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class AdquisicionsControllerTest {
    //Prueba unitaria para el TP
    //Si es que la diferencia de dias en la que el usuario quiere registrar con la fecha de publicacion del place es mayor a 4 entonces se realizará la insercion a la tabla "sale"
    @InjectMocks
    private AdquisicionsController saleTableController;
    @Mock
    private AdquisicionsServiceImpl saleTableService;
    @Mock
    private ITravellerService usersService;
    @Mock
    private IPlacesService placesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createSaleTable_validRequest() throws Exception{
        Traveller user = new Traveller();
        user.setId(1L);

        Places places = new Places();
        places.setId(2L);
        places.setDate("31/09/2023"); // Fecha actual + 4 días

        System.out.println(places.getDate());
        Adquisicions savedSaleTable = new Adquisicions();
        savedSaleTable.setId(3L);
        savedSaleTable.setDate("28/09/2023");
        Mockito.when(usersService.getById(1L)).thenReturn(Optional.of(user));
        Mockito.when(placesService.getById(2L)).thenReturn(Optional.of(places));
        Mockito.when(saleTableService.save(any(Adquisicions.class))).thenReturn(savedSaleTable);

        System.out.println(user);
        System.out.println(places);

        Adquisicions requestSaleTable = new Adquisicions();
        ResponseEntity<Adquisicions> response = saleTableController.CreateAdquisicion(2L, 1L, requestSaleTable);


        Mockito.verify(usersService).getById(1L);
        Mockito.verify(placesService).getById(2L);

        Mockito.verify(saleTableService).save(any(Adquisicions.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedSaleTable, response.getBody());
    }



}
