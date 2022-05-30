package com.example.cabbooking.model.vehicle;

import com.example.cabbooking.model.driver.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Hardeep Singh
 */
@Entity
@Table(name = "vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String vehicleNumber;
//    @Column(name = "make")
    private String make;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;

}
