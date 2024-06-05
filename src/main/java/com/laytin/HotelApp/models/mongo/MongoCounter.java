package com.laytin.HotelApp.models.mongo;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@Document(collection = "counters")
public class MongoCounter {
    @Id
    private String id;
    private int seq;
}
