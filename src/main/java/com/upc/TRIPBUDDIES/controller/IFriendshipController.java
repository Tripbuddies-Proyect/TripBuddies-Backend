package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.entities.Friendship;
import com.upc.TRIPBUDDIES.entities.Traveller;
import com.upc.TRIPBUDDIES.service.IFriendshipService;
import com.upc.TRIPBUDDIES.service.ITravellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/friendship")
@Api(tags = "Friendship", value = "Web Service RESTFul of Friendship")
public class IFriendshipController {
    private final IFriendshipService friendshipService;
    private final ITravellerService travellerService;

    public IFriendshipController(IFriendshipService friendshipService, ITravellerService travellerService) {
        this.friendshipService = friendshipService;
        this.travellerService = travellerService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search friendship by Id", notes = "Method for find a friendship by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Friendship found by Id"),
            @ApiResponse(code = 404, message = "Friendship Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Friendship>> findAllFriends(){
        try {
            List<Friendship> friendship = friendshipService.getAll();
            if(friendship.size() >0)
                return new ResponseEntity<>(friendship, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search friendship by User Id", notes = "Method for find a friendship by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Friendship found by Id"),
            @ApiResponse(code = 404, message = "Friendship Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Friendship> getFriendById(@PathVariable("id") Long id){
        try {
            Optional<Friendship> friendship = friendshipService.getById(id);
            if(friendship == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(friendship.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search friendship by Id", notes = "Method for find a friendship by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Friendship found by Id"),
            @ApiResponse(code = 404, message = "Friendship Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Friendship>> getFriendByUser(@PathVariable("id") Long id){
        try {
            Optional<Traveller> traveller = travellerService.getById(id);
            if(!traveller.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else{
                List<Friendship> friendship = friendshipService.findByUserId(id);
                return new ResponseEntity<>(friendship, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Friendship", notes = "Method to create a new Friendship")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Friendship created"),
            @ApiResponse(code = 404, message = "Friendship not created"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Friendship> insertFriendship(@PathVariable("id") Long id, @RequestBody Friendship friendship){
        try {
            Optional<Traveller> friendshipNew = travellerService.getById(id);
            if(!friendshipNew.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                friendship.setUser(friendshipNew.get());
                Friendship friendshipCreate = friendshipService.save(friendship);
                return ResponseEntity.status(HttpStatus.CREATED).body(friendshipCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Friendship", notes = "Method to delete Friendship")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Friendship deleted"),
            @ApiResponse(code = 404, message = "Friendship not deleted"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Friendship> deleteFriendship(@PathVariable("id") Long id){
        try {
            Optional<Friendship> friendshipDelete = friendshipService.getById(id);
            if(!friendshipDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                friendshipService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
