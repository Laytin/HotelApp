package com.laytin.HotelApp.models.mongo;

import com.laytin.HotelApp.models.AmenityEnum;
import com.laytin.HotelApp.models.abstr.HotelAbstr;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "hotel")
public class HotelMongo extends HotelAbstr {
    @Id
    private int id;
    private String name;
    private String description;
    private String brand;
    private AddressMongo address;
    private ContactsMongo contacts;
    private ArrivalTimeMongo arrivalTime;
    private List<AmenityEnum> amenities;
}
