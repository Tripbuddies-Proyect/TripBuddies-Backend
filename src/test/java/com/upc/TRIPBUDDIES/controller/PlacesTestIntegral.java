package com.upc.TRIPBUDDIES.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.TRIPBUDDIES.entities.Bussiness;
import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.service.IBussinessService;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.*;

@WebMvcTest(controllers = PlacesControlller.class)

public class PlacesTestIntegral {

    @MockBean
    private IPlacesService placesService;

    @MockBean
    private IBussinessService businessService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePostForPlaceWithBusiness() throws Exception {
        // Datos de ejemplo
        Long placeId = 1L;
        Places place = new Places();
        place.setId(1L); // ID simulado
        place.setName("Ejemplo de lugar");
        place.setDescription("Descripción del lugar de ejemplo");
        place.setImageUrl("https://ejemplo.com/imagen.jpg");
        place.setPrice(50.0f);
        place.setLocation("Ciudad Ejemplo");
        place.setCountry("País Ejemplo");

        Bussiness business = new Bussiness();
        business.setId(1L); // ID simulado
        business.setRuc("12345678901");
        business.setOwner("Propietario del negocio");
        business.setName("Nombre del negocio");
        business.setAddress("Dirección del negocio");
        business.setCountry("País del negocio");
        business.setCity("Ciudad del negocio");

        // Configura el comportamiento de los mocks
        when(businessService.getById(placeId)).thenReturn(Optional.of(business));
        when(placesService.save(any(Places.class))).thenReturn(place);

        // Realiza la solicitud HTTP simulada
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/places/{id}", placeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(place))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L)) // Verifica que se haya creado el lugar correctamente
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Ejemplo de lugar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Descripción del lugar de ejemplo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value("https://ejemplo.com/imagen.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(50.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("Ciudad Ejemplo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country").value("País Ejemplo"));

        // Verifica que se llamaron los métodos adecuados
        verify(businessService, times(1)).getById(placeId);
        verify(placesService, times(1)).save(any(Places.class));
    }
}
