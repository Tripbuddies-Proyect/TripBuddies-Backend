package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.entities.Message;
import com.upc.TRIPBUDDIES.entities.User;
import com.upc.TRIPBUDDIES.service.CrudService;
import com.upc.TRIPBUDDIES.service.IMessageService;
import com.upc.TRIPBUDDIES.service.IUsersService;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MessageControllerTest {

    @Mock
    private IMessageService messageService;
    @Mock
    private IUsersService usersService;
    @InjectMocks
    private MessageController messageController;


    Message message;


    @BeforeEach
    void setUp(){
        //Inicializacion de mockito
        MockitoAnnotations.initMocks(this);

        message= new Message();
        message.setEmitter(new User());
        message.setMessage("Mensaje prueba");
        message.setId(1L);
    }
    @Test
    void getAllMessagesByUserId(){
    }


    @Test
    void findMessageById() {

    }

    @SneakyThrows
    @Test
    void insertMessage() {
        Optional<User> user =Optional.of(new User());
        when(usersService.getById(anyLong())).thenReturn(user);
        Message savedMessage = new Message();
        when(messageService.save(message)).thenReturn(savedMessage);

        ResponseEntity<Message> responseEntity = messageController.insertMessage(message, message.getId());

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedMessage, responseEntity.getBody());
    }
}