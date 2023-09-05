import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.TRIPBUDDIES.controller.ReviewController;
import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.entities.Review;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import com.upc.TRIPBUDDIES.service.IReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// PRIMERA PRUEBA UNITARIA CON MOCKITO PUESTO FASILITA SIMULAR LOS DATOS
public class PruevaUnitaria {

    private MockMvc mockMvc;
    private IReviewService reviewService;
    private IPlacesService placesService;
    private ReviewController reviewController;

    @BeforeEach
    public void setup() {
        reviewService = Mockito.mock(IReviewService.class);
        placesService = Mockito.mock(IPlacesService.class);
        reviewController = new ReviewController(reviewService, placesService, null);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    public void testCreateReview() throws Exception {

        // DATOS DE EJEMPLO QUE PROPUSIMOS
        Long placeId = 1L;
        Review reviewToCreate = new Review();
        reviewToCreate.setReviewText("Nueva reseña");

        Places place = new Places();
        place.setId(placeId);

        // Mockito para IPlacesService para devolver un lugar de ejemplo
        Mockito.when(placesService.getById(placeId)).thenReturn(java.util.Optional.of(place));

        // Mock de IReviewService para devolver la revisión creada
        Mockito.when(reviewService.save(reviewToCreate)).thenReturn(reviewToCreate);

        // Realiza una solicitud POST simulada
        mockMvc.perform(post("/api/reviews/places/{placeId}", placeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reviewToCreate)))
                .andExpect(status().isCreated()); // Esperamos un código de respuesta 201 (CREATED)
    }
}
