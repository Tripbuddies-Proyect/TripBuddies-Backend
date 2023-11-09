package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
@ContextConfiguration
public class PlaceSteps {

    @Mock
    private IPlacesService placesService;

    @InjectMocks
    private Places newPlace;

    public PlaceSteps() {
        openMocks(this);
    }

    @Given("el operador de turismo con email {string} y contraseña {string} está autenticado")
    public void el_operador_de_turismo_está_autenticado(String email, String password) {
        // Aquí puedes incluir la lógica de autenticación o asumir que el usuario está autenticado
    }

    @When("el operador crea un nuevo lugar con el título {string}, descripción {string}, imagen {string}, precio {string}, destino {string}, fecha {string}, hora {string} y origen {string}")
    public void el_operador_crea_un_nuevo_lugar(String titulo, String descripcion, String imagen, String precio, String destino, String fecha, String hora, String origen) throws Exception {
        // Convertir el precio de String a Float
        Float precioFloat = Float.valueOf(precio);

        // Preparar los datos de entrada
        newPlace = new Places();
        newPlace.setName(titulo);
        newPlace.setDescription(descripcion);
        newPlace.setImageUrl(imagen);
        newPlace.setPrice(precioFloat);
        newPlace.setDestino(destino);
        newPlace.setDate(fecha);
        newPlace.setHour(hora);
        newPlace.setOrigen(origen);

        // Configurar el mock para simular el guardado exitoso del lugar
        Mockito.when(placesService.save(any(Places.class))).thenReturn(newPlace);

        // Ejecutar la acción de guardado
        newPlace = placesService.save(newPlace);
    }


    @Then("el nuevo lugar turístico debería ser guardado con el título {string}")
    public void el_nuevo_lugar_turístico_debería_ser_guardado(String titulo) {
        // Afirmar que el lugar ha sido guardado con el título correcto
        assertEquals(titulo, newPlace.getName());
    }
}
