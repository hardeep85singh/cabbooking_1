package com.example.cabbooking.service;

import com.example.cabbooking.model.Location;
import com.example.cabbooking.model.driver.Driver;
import com.example.cabbooking.model.user.UserLocation;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Hardeep Singh
 */
public interface BookRideService {

    List<Driver> findRide(String username, Location source, Location destination);

    String chooseRide(String username);

    Double calculateBill(String username);

}
