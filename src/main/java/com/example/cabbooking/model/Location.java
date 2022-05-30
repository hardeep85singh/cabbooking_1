package com.example.cabbooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hardeep Singh
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location {
    @JsonProperty
    private double X;
    @JsonProperty
    private double Y;
}
