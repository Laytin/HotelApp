package com.laytin.HotelApp.models.mongo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laytin.HotelApp.models.abstr.ArrivalTimeAbstr;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class ArrivalTimeMongo extends ArrivalTimeAbstr {
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="HH:mm")
    private LocalTime checkIn;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="HH:mm")
    private LocalTime checkOut;
}
