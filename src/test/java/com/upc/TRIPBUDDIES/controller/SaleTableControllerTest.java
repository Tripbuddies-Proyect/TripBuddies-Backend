package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.entities.SaleTable;
import com.upc.TRIPBUDDIES.entities.User;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import com.upc.TRIPBUDDIES.service.IUsersService;
import com.upc.TRIPBUDDIES.service.impl.SaleTableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SaleTableControllerTest {
    @InjectMocks
    private SaleTableController saleTableController;

    @Mock
    private SaleTableService saleTableService;

    @Mock
    private IUsersService usersService;

    @Mock
    private IPlacesService placesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSaleTable_validRequest() throws Exception {
        User user = new User();
        user.setId(1L);

        Places places = new Places();
        places.setId(2L);
        places.setDate(new Date(System.currentTimeMillis() + 6 * 24 * 60 * 60 * 1000)); // Fecha actual + 4 días

        SaleTable savedSaleTable = new SaleTable();
        savedSaleTable.setId(3L);

        Mockito.when(usersService.getById(1L)).thenReturn(Optional.of(user));
        Mockito.when(placesService.getById(2L)).thenReturn(Optional.of(places));
        Mockito.when(saleTableService.save(user, places)).thenReturn(savedSaleTable);

        SaleTable requestSaleTable = new SaleTable();
        ResponseEntity<SaleTable> response = saleTableController.createSaleTable(1L, 2L, requestSaleTable);


        Mockito.verify(usersService).getById(1L);
        Mockito.verify(placesService).getById(2L);
        Mockito.verify(saleTableService).save(user, places);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedSaleTable, response.getBody());
    }
}