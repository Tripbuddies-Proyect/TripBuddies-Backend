package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.entities.SaleTable;
import com.upc.TRIPBUDDIES.entities.User;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import com.upc.TRIPBUDDIES.service.ISaleTableService;
import com.upc.TRIPBUDDIES.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/saleTable")
@Api(tags = "SaleTable", value = "Web Service RESTFul of SaletABLE")
public class SaleTableController {

    private final IUsersService usersService;
    private final IPlacesService placesService;
    private final ISaleTableService saleTableService;

    @Autowired
    public SaleTableController(ISaleTableService saleTableService, IPlacesService placesService, IUsersService usersService) {
        this.usersService = usersService;
        this.placesService = placesService;
        this.saleTableService = saleTableService;
    }


    @PostMapping(value = "/{id_use}/{id_place}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Se agregó correctamente"),
            @ApiResponse(code = 404, message = "No se permite la creación"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<SaleTable> createSaleTable(@PathVariable("id_use") Long id_user, @PathVariable("id_place") Long id_place, @RequestBody SaleTable saleTable) {
        try {
            Optional<User> user = usersService.getById(id_user);
            Optional<Places> places = placesService.getById(id_place);

            if (user.isPresent() && places.isPresent()) {
                Date fechaActual = new Date();
                Date fechaPlaces = places.get().getDate();

                long diferenciaEnMilisegundos = fechaPlaces.getTime() - fechaActual.getTime();
                long diferenciaEnDias = diferenciaEnMilisegundos / (1000 * 60 * 60 * 24);

                System.out.println("Diferencia en días: " + diferenciaEnDias);
                if (diferenciaEnDias >= 4) {
                    // En lugar de crear una instancia SaleTable aquí, llama al método de servicio correspondiente
                    SaleTable saleTable1 = saleTableService.save(user.get(), places.get());
                    return new ResponseEntity<>(saleTable1, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleTable>> findAllSaleTable() {
        try {
            List<SaleTable> saleTables = saleTableService.getAll();
            if (saleTables.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(saleTables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}