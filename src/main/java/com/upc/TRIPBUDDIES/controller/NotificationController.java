package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.entities.Notification;
import com.upc.TRIPBUDDIES.entities.Traveller;
import com.upc.TRIPBUDDIES.entities.User;
import com.upc.TRIPBUDDIES.service.INotificationService;
import com.upc.TRIPBUDDIES.service.ITravellerService;
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
@RequestMapping("/api/v1/users/{userId}/notifications")
@Api(tags = "Notification", value = "Web Service RESTFul of Notification")
public class NotificationController {

    private final INotificationService notificationService;
    private final ITravellerService travellerService;

    private final IUsersService usersService;




    public NotificationController(INotificationService notificationService, ITravellerService travellerService,IUsersService usersService) {
        this.notificationService = notificationService;
        this.travellerService = travellerService;
        this.usersService = usersService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Notifications", notes = "Method to list all Notifications")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Notifications founds"),
            @ApiResponse(code = 404, message = "Notifications Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Notification>> findAllNotifications(@PathVariable("userId")Long userId) {
        try {
            List<Notification> notifications = notificationService.getAll();
            Predicate<Notification> byId = notification -> (notification.getReceiver().getId() == userId);
            notifications = notifications.stream().filter(byId).collect(Collectors.toList());
            notifications = notifications.stream().sorted((n1, n2) -> n2.getId().compareTo(n1.getId())).collect(Collectors.toList());
            if(notifications.size()>0)
                return new ResponseEntity<>(notifications, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{receiverId}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Notification by Id", notes = "Method for find a Notification by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Notification found by Id"),
            @ApiResponse(code = 404, message = "Notification Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Notification> findNotificationById(@PathVariable("id")Long id, @PathVariable("receiverId")Long receiverId) {
        try {
            Optional<Notification> notification = notificationService.getById(id);
            if(!notification.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(notification.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @SneakyThrows
    @PostMapping(value="/{receiverId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Notification", notes = "Method for create a Notification")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Notification created"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Notification> insertNotification(@Valid @RequestBody Notification notification, @PathVariable(value = "userId") Long userId, @PathVariable("receiverId") Long receiverId){
        Optional<User> emitter = usersService.getById(userId);
        Optional<User> receiver = usersService.getById(receiverId);
        notification.setEmitter(emitter.get());
        notification.setReceiver(receiver.get());
        try {
            Notification newNotification = notificationService.save(notification);
            return ResponseEntity.status(HttpStatus.CREATED).body(newNotification);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @SneakyThrows
    @PutMapping(value="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Notification", notes = "Method for update a Notification")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Notification updated"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Notification> updateNotification(@PathVariable("id")Long id, @Valid @RequestBody Notification notification, @PathVariable(value = "userId") Long userId){
        Optional<Traveller> emitter = travellerService.getById(userId);
        notification.setEmitter(emitter.get());
        try {
            Optional<Notification> currentNotification = notificationService.getById(id);
            if(!currentNotification.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            notification.setId(id);
            notificationService.save(notification);
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Notification", notes = "Method for delete a Notification")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Notification deleted"),
            @ApiResponse(code = 404, message = "Notification Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Notification> deleteNotification(@PathVariable("id")Long userId, @PathVariable("id")Long id){
        try {
            Optional<Notification> notificationDelete = notificationService.getById(id);
            if(!notificationDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            notificationService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/LastNotificationTraveller", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Last Notification", notes = "Method for get the last Notification")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Notification found"),
            @ApiResponse(code = 404, message = "Notification Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Notification>> getLastNotificationTraveller(@PathVariable("userId")Long userId) {
        try {
            List<Notification> Lastnotifications = notificationService.findLastNotificationTraveller(userId);
            Lastnotifications = Lastnotifications.stream().sorted((n1, n2) -> n2.getId().compareTo(n1.getId())).collect(Collectors.toList());
            if (Lastnotifications.size() > 0)
                return new ResponseEntity<>(Lastnotifications, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/LastNotificationBussiness", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Last Notification", notes = "Method for get the last Notification")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Notification found"),
            @ApiResponse(code = 404, message = "Notification Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Notification>> getLastNotificationCompany(@PathVariable("userId")Long userId) {
        try {
            List<Notification> Lastnotifications = notificationService.findLastNotificationBussiness(userId);
            Lastnotifications = Lastnotifications.stream().sorted((n1, n2) -> n2.getId().compareTo(n1.getId())).collect(Collectors.toList());
            if (Lastnotifications.size() > 0)
                return new ResponseEntity<>(Lastnotifications, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
