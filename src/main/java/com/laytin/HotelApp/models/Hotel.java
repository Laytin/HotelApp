package com.laytin.HotelApp.models;

import com.laytin.HotelApp.models.abstr.HotelAbstr;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="hotel")
@NoArgsConstructor
public class Hotel extends HotelAbstr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    @OneToOne(mappedBy = "hotel",fetch = FetchType.EAGER)
    private Address address;
    @OneToOne(mappedBy = "hotel",fetch = FetchType.EAGER)
    private Contacts contacts;
    @OneToOne(mappedBy = "hotel",fetch = FetchType.EAGER)
    private ArrivalTime arrivalTime;
    @ElementCollection(targetClass = AmenityEnum.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<AmenityEnum> amenities;
}
