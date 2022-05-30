package com.example.cabbooking.model.driver;

import com.example.cabbooking.model.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author Hardeep Singh
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigInteger phoneNumber;
    private boolean status;
    private double earnings;
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_location_id")
    private DriverLocation driverLocation;

//    private double x_coordinates;
//    private double y_coordinates;
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
//    @JoinTable(name = "driver_vehicle",
//               joinColumns = @JoinColumn(name = "driver_id"),
//               inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Vehicle vehicle;
}
