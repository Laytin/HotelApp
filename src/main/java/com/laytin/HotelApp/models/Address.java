package com.laytin.HotelApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Data
@Entity
@Table(name = "address")
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @OneToOne()
    @JoinColumn(name = "hotel_id")
    @JsonIgnore
    @ToString.Exclude
    private Hotel hotel;

    private String houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;

    @Override
    public String toString() {
        return "" + houseNumber + " " + street + ", "+city + ", "+postCode + ", "+ country;
    }
}
