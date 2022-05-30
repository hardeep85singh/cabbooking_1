package com.example.cabbooking.service;

import com.example.cabbooking.model.Location;
import com.example.cabbooking.model.driver.Driver;
import com.example.cabbooking.model.user.User;
import com.example.cabbooking.model.user.UserDestinationLocation;
import com.example.cabbooking.model.user.UserLocation;
import com.example.cabbooking.repository.DriverRepository;
import com.example.cabbooking.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Hardeep Singh
 */
@Service
@Slf4j
public class BookRideServiceImpl implements BookRideService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    DriverService driverService;

    private double DRIVER_TO_USER_DISTANCE = 5.0;
    private double RATE = 2.0;

    @Autowired
    public BookRideServiceImpl(UserRepository userRepository, DriverRepository driverRepository,
                               DriverService driverService){
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.driverService = driverService;
    }

    @Override
    public List<Driver> findRide(String username, Location source, Location destination) {
        User user = userRepository.findByUsername(username);
        UserDestinationLocation userDestinationLocation = new UserDestinationLocation();
        userDestinationLocation.setUser(user);
        userDestinationLocation.setX(destination.getX());
        userDestinationLocation.setY(destination.getY());
        user.setUserDestinationLocation(userDestinationLocation);
        UserLocation userLocation = new UserLocation();
        userLocation.setUser(user);
        userLocation.setX(source.getX());
        userLocation.setY(source.getY());
        user.setUserLocation(userLocation);
        userRepository.save(user);

        List<Driver> drivers = getAllDrivers();

        double userToDestinationDistance = getDistance(source, destination);

        List<Driver> availableDrivers = new ArrayList<>();
        availableDrivers = findDrivers(drivers, userToDestinationDistance, availableDrivers);
        return availableDrivers;
    }

    @Override
    public String chooseRide(String username) {
        List<Driver> drivers = getAllDrivers();
        Driver driver = drivers.get(0);
        driver.setStatus(false);
        Double earnings = calculateBill(username);
        driver.setEarnings(earnings);
        driverRepository.save(driver);
        return driver.getName();
    }

    @Override
    public Double calculateBill(String username) {
        User user = userRepository.findByUsername(username);
        double rideDistance = getDistance(user.getUserLocation(), user.getUserDestinationLocation());
        Double bill = rideDistance * RATE;
        return bill;
    }

    private double getDistance(Location source, Location destination) {
        return Math.abs(source.getX() - destination.getX()) + Math.abs(source.getY() - destination.getY());
    }

    private List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        Iterable<Driver> iterable = driverRepository.findAll();
        for (Driver driver: iterable){
            drivers.add(driver);
        }
        return drivers;
    }

    private List<Driver> findDrivers(List<Driver> drivers, double userToDestinationDistance, List<Driver> availableDrivers) {
        for(Driver driver : drivers){
            if(driver.isStatus() == true && DRIVER_TO_USER_DISTANCE <= userToDestinationDistance){
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

}

