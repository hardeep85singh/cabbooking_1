package com.example.cabbooking.model.user;

import com.example.cabbooking.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

/**
 * @author Hardeep Singh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")

public class User {
//    @Id
//    @GeneratedValue
//    private Integer id;
    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
    private String password;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_location_id")
    private UserLocation userLocation;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_destination_location_id")
    private UserDestinationLocation userDestinationLocation;
//    private double x_coordinates;
//    private double y_coordinates;
}
