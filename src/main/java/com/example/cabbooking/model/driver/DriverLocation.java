package com.example.cabbooking.model.driver;

import com.example.cabbooking.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

/**
 * @author Hardeep Singh
 */
@Entity
@Table(name = "driver_location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverLocation extends Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "driverLocation")
    private Driver driver;
}
