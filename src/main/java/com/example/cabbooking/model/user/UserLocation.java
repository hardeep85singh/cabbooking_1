package com.example.cabbooking.model.user;

import com.example.cabbooking.model.Location;
import com.example.cabbooking.model.driver.Driver;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

/**
 * @author Hardeep Singh
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_location")
public class UserLocation extends Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

//    @JsonProperty
//    private double X;
//    @JsonProperty
//    private double Y;

    @OneToOne(mappedBy = "userLocation")
    private User user;
}
