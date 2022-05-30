package com.example.cabbooking.controller;

import com.example.cabbooking.model.Location;
import com.example.cabbooking.model.user.User;
import com.example.cabbooking.model.user.UserLocation;
import com.example.cabbooking.repository.UserRepository;
import com.example.cabbooking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author Hardeep Singh
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        log.info("Inside UserController, addUser method. User details : {}", user);
        User savedUser = userService.addUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(savedUser.getUsername())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/user")
    public ResponseEntity<Object> updateUser(@Param ("username") String username,
                                           @RequestBody User user){
        log.info("Inside UserController, updateUser method. User details : {}", user);
        User updatedUser = userService.updateUser(username, user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(updatedUser.getUsername())
                .toUri();
        return ResponseEntity.ok(location);
    }

    @PostMapping("/user/location")
    public ResponseEntity<Object> updateUserLocation(@Param("username") String username,
                                                          @RequestBody UserLocation location){
        log.info("Inside UserController, updateUserLocation method. Username: {}. Location: {}", username, location);
        userService.updateUserLocation(username, location);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(location.getX(), location.getY())
                .toUri();

        return ResponseEntity.ok(uri);
    }
}
