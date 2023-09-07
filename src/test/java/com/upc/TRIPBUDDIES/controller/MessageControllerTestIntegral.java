package com.upc.TRIPBUDDIES.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.TRIPBUDDIES.entities.Message;
import com.upc.TRIPBUDDIES.entities.User;
import com.upc.TRIPBUDDIES.service.IBussinessService;
import com.upc.TRIPBUDDIES.service.IMessageService;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import com.upc.TRIPBUDDIES.service.IUsersService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;


@AutoConfigureMockMvc
@WebMvcTest(MessageController.class)
class MessageControllerTestIntegral {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUsersService usersService;
    @MockBean
    private IMessageService messageService;

    @MockBean
    private IBussinessService businessService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void insertMessage() throws Exception{

        //String messageContent ="prueba";
        //mockMvc.perform(MockMvcRequestBuilders.post("api/v1/users/{userId}/messages/{receiverId}",2,3)
          //      .contentType(MediaType.APPLICATION_JSON)
            //    .content(messageContent))
              //  .andExpect(MockMvcResultMatchers.status().isCreated());

        User user = new User();
        Message message = new Message(); // Define el objeto message

        Mockito.when(usersService.getById(anyLong())).thenReturn(Optional.of(user));
        Mockito.when(messageService.save(Mockito.any(Message.class))).thenReturn(new Message());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users/{userId}/messages/{receiverId}", 2, 3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(message))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        // Verificar que los métodos de las dependencias simuladas se hayan llamado correctamente
        Mockito.verify(usersService).getById(Mockito.anyLong());
        Mockito.verify(messageService).save(Mockito.any(Message.class));
    }
}