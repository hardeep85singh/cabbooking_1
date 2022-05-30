package com.example.cabbooking.service;

import com.example.cabbooking.model.driver.Driver;
import com.example.cabbooking.model.driver.DriverLocation;
import com.example.cabbooking.model.vehicle.Vehicle;
import com.example.cabbooking.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hardeep Singh
 */
@Service
@Slf4j
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    @Override
    public DriverLocation addDriver(Driver driver, Vehicle vehicle, DriverLocation driverLocation) {
        driverRepository.save(driver);
        return driverLocation;
    }

    @Override
    public DriverLocation updateDriverLocation(String driverName,
                                               DriverLocation driverLocation) {
        log.info("Inside updateDriverLocation method: {}, {}, {}", driverName, driverLocation.getX(), driverLocation.getY());
        Iterable<Driver> drivers = driverRepository.findAll();
        for(Driver driver : drivers){
            if(driver.getName().equals(driverName)){
                driver.setDriverLocation(driverLocation);
//                driver.setX_coordinates(driverLocation.getX());
//                driver.setY_coordinates(driverLocation.getY());
                driverRepository.save(driver);
            }
        }
        return driverLocation;
    }

    @Override
    public boolean changeDriverStatus(String driverName, boolean status) {
        Iterable<Driver> drivers = driverRepository.findAll();
        for(Driver driver : drivers){
            if(driver.getName().equals(driverName)){
                driver.setStatus(status);
                driverRepository.save(driver);
            }
        }
        return status;
    }

    @Override
    public Double totalEarnings(String drivername) {
        Driver driver = driverRepository.findByDriverName(drivername);
        return driver.getEarnings();
    }
}
