package com.example.cabbooking.controller;

import com.example.cabbooking.model.Location;
import com.example.cabbooking.model.driver.Driver;
import com.example.cabbooking.model.user.UserLocation;
import com.example.cabbooking.service.BookRideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Hardeep Singh
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class BookRideController {
    @Autowired
    BookRideService bookRideService;

    @Autowired
    public BookRideController(BookRideService bookRideService){
        this.bookRideService = bookRideService;
    }


    @GetMapping("/user/find-ride")
    public ResponseEntity<List<String>> findRide(@Param("username") String username,
                                                 @RequestBody Map<String, Location> location){
        log.info("Inside BookRideController. Inside findRide method. Username: {}. Destination: {}", username, location);
        Location source = location.get("source");
        Location destination = location.get("destination");
        List<Driver> availableDrivers = bookRideService.findRide(username, source, destination);
        List<String> drivers = new ArrayList<>();
        if(availableDrivers.size() == 0){
            drivers.add("No Driver is available");
            return ResponseEntity.ok(drivers);
        } else{
            for(Driver driver: availableDrivers){
                drivers.add(driver.getName());
            }
        }
        return ResponseEntity.ok(drivers);

    }

    @GetMapping("/user/choose-ride")
    public ResponseEntity<String> chooseRide(@Param("username") String username){
        log.info("Inside BookRideController. Inside chooseRide method. Username: {}. ", username);
        return ResponseEntity.ok(bookRideService.chooseRide(username));
    }

    @GetMapping("/user/calculate-bill")
    public ResponseEntity<Double> calculateBill(@Param("username") String username){
        log.info("Inside BookRideController. Inside calculateBill method. Username: {}. ", username);
        return ResponseEntity.ok(bookRideService.calculateBill(username));
    }
}
