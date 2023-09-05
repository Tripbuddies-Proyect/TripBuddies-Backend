package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.entities.Message;
import com.upc.TRIPBUDDIES.entities.User;
import com.upc.TRIPBUDDIES.service.IMessageService;
import com.upc.TRIPBUDDIES.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users/{userId}/messages")
@Api(tags = "Messages", value = "Web Service RESTFul of Messages")
public class MessageController {

    private final IMessageService messageService;
    private final IUsersService userService;

    public MessageController(IMessageService messageService, IUsersService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get All Messages by User ID", notes = "Method to get all messages by user ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Messages found"),
            @ApiResponse(code = 404, message = "Messages not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<Message>> getAllMessagesByUserId(@PathVariable("userId") Long userId) {
        try {
            List<Message> messages = messageService.getAll();
            Predicate<Message> byUserId = message ->
                    message.getEmitter().getId().equals(userId) || message.getReceiver().getId().equals(userId);
            List<Message> userMessages = messages.stream().filter(byUserId).collect(Collectors.toList());
            userMessages = userMessages.stream().sorted((m1, m2) -> m1.getId().compareTo(m2.getId())).collect(Collectors.toList());

            if (!userMessages.isEmpty()) {
                return ResponseEntity.ok(userMessages);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





    @GetMapping(value = "/{receiverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Messagess", notes = "Method to list all Messages")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Messages founds"),
            @ApiResponse(code = 404, message = "Messages Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Message>> findAllMessages(@PathVariable("userId")Long userId, @PathVariable("receiverId")Long receiverId) {
        try {
            List<Message> messages = messageService.getAll();
            Predicate<Message> byId = message -> (message.getEmitter().getId() == userId && message.getReceiver().getId() == receiverId)
                    || ( message.getEmitter().getId() == receiverId && message.getReceiver().getId() == userId);
            messages = messages.stream().filter(byId).collect(Collectors.toList());
            messages = messages.stream().sorted((m1, m2) -> m1.getId().compareTo(m2.getId())).collect(Collectors.toList());
            if(messages.size()>0)
                return new ResponseEntity<>(messages, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{receiverId}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Message by Id", notes = "Method for find a Message by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Message found by Id"),
            @ApiResponse(code = 404, message = "Message Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Message> findMessageById(@PathVariable("id")Long id, @PathVariable("receiverId")Long receiverId){
        try {
            Optional<Message> message = messageService.getById(id);
            if(!message.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(message.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @SneakyThrows
    @PostMapping(value = "/{receiverId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Message", notes = "Method for create a Message")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Message created"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Message> insertMessage(@Valid @RequestBody Message message, @PathVariable(value = "userId") Long userId){
        Optional<User> emitter = userService.getById(userId);
        message.setEmitter(emitter.get());
        try{
            Message newMessage = messageService.save(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(newMessage);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @SneakyThrows
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Message", notes = "Method for update a Message")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Message updated"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Message> updateMessage(@PathVariable("id") Long id, @Valid @RequestBody Message message, @PathVariable(value = "userId") Long userId){
        Optional<User> emitter = userService.getById(userId);
        message.setEmitter(emitter.get());
        try {
            Optional<Message> currentMessage = messageService.getById(id);
            if(!currentMessage.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            message.setId(id);
            messageService.save(message);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Message", notes = "Method for delete a Message")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Message deleted"),
            @ApiResponse(code = 404, message = "Message Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Message> deleteMessage(@PathVariable("id") Long id){
        try {
            Optional<Message> message = messageService.getById(id);
            if(!message.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            messageService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/LastMessageTraveller", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Last Message", notes = "Method for get the last Message")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Last Message Found"),
            @ApiResponse(code = 404, message = "Last Message Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Message>> getLastMessageTraveller(@PathVariable(value = "userId") Long userId){
        try {
            List<Message> Lastmessages = messageService.findLastMessageTraveller(userId);
            Lastmessages = Lastmessages.stream().sorted((m1, m2) -> m2.getId().compareTo(m1.getId())).collect(Collectors.toList());
            if(Lastmessages.size()>0)
                return new ResponseEntity<>(Lastmessages, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/LastMessageBussiness", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Last Message", notes = "Method for get the last Message")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Last Message Found"),
            @ApiResponse(code = 404, message = "Last Message Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Message>> getLastMessageBussiness(@PathVariable(value = "userId") Long userId){
        try {
            List<Message> Lastmessages = messageService.findLastMessageBussiness(userId);
            Lastmessages = Lastmessages.stream().sorted((m1, m2) -> m2.getId().compareTo(m1.getId())).collect(Collectors.toList());
            if(Lastmessages.size()>0)
                return new ResponseEntity<>(Lastmessages, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
