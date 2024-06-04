package com.laytin.HotelApp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "arrivaltime")
@NoArgsConstructor
public class ArrivalTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @OneToOne
    @JoinColumn(name = "hotel_id")
    @JsonIgnore
    @ToString.Exclude
    private Hotel hotel;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="HH:mm")
    private LocalTime checkIn;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="HH:mm")
    private LocalTime checkOut;
}
