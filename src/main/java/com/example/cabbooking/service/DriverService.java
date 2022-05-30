package com.example.cabbooking.service;

import com.example.cabbooking.model.driver.Driver;
import com.example.cabbooking.model.driver.DriverLocation;
import com.example.cabbooking.model.vehicle.Vehicle;

/**
 * @author Hardeep Singh
 */
public interface DriverService {
    DriverLocation addDriver(Driver driver, Vehicle vehicle, DriverLocation driverLocation);

    DriverLocation updateDriverLocation(String driverName,
                                        DriverLocation driverLocation);

    boolean changeDriverStatus(String driverName, boolean status);

    Double totalEarnings(String drivername);
}
