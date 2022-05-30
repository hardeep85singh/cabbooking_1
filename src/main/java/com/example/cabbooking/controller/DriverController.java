package com.example.cabbooking.controller;

import com.example.cabbooking.model.driver.Driver;
import com.example.cabbooking.model.driver.DriverLocation;
import com.example.cabbooking.model.user.User;
import com.example.cabbooking.model.vehicle.Vehicle;
import com.example.cabbooking.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

/**
 * @author Hardeep Singh
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    public DriverController (DriverService driverService){
        this.driverService = driverService;
    }


    @PostMapping("/driver")
    public ResponseEntity<Object> addDriver(@RequestBody Driver driver){
        log.info("Inside DriverController, addDriver method. Driver details : {}", driver);

        Vehicle vehicle = new Vehicle();
        vehicle.setDriver(driver);
        vehicle.setVehicleNumber(driver.getVehicle().getVehicleNumber());
        vehicle.setMake(driver.getVehicle().getVehicleNumber());

        DriverLocation driverLocation = new DriverLocation();
        driverLocation.setX(driver.getDriverLocation().getX());
        driverLocation.setY(driver.getDriverLocation().getY());
        log.info(String.valueOf(driverLocation.getX()));

        driverService.addDriver(driver, vehicle, driverLocation);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{driver}")
                .buildAndExpand("")
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/driver")
    public ResponseEntity<Object> updateDriverLocation(@Param("drivername") String drivername,
                                             @RequestBody DriverLocation driverLocation){
        log.info("Inside DriverController, updateDriverLocation method. Driver Location details : {}", driverLocation);
        DriverLocation driverLocation1 = driverService.updateDriverLocation(drivername, driverLocation);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{drivername}")
                .buildAndExpand(driverLocation.getX()+ driverLocation.getY())
                .toUri();
        return ResponseEntity.ok(location);
    }

    @PostMapping("/driver/status")
    public ResponseEntity<Object> changeDriverStatus(@Param("drivername") String drivername,
                                                     @Param("status") boolean status){
        log.info("Inside DriverController, changeDriverStatus method. Driver status : {}", status);
        boolean st = driverService.changeDriverStatus(drivername, status);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{drivername}")
                .buildAndExpand(status)
                .toUri();
        return ResponseEntity.ok(location);
    }

    @GetMapping("driver/earnings")
    public ResponseEntity<Double> driverTotalEarnings(@Param("drivername") String drivername){
        return ResponseEntity.ok(driverService.totalEarnings(drivername));
    }
}
