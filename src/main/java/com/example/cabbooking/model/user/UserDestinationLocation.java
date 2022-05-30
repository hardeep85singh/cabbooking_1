package com.example.cabbooking.model.user;

import com.example.cabbooking.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

/**
 * @author Hardeep Singh
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_destination_location")
public class UserDestinationLocation extends Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
//
//    @JsonProperty
//    private double X;
//    @JsonProperty
//    private double Y;

    @OneToOne(mappedBy = "userDestinationLocation")
    private User user;
}
