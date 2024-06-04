package com.laytin.HotelApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Time;
import java.time.LocalTime;
@Tag(name = "Arrival Time DTO", description = "ArrivalTime DTO")
@Data
public class ArrivalTimeDTO {
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="HH:mm")
    @Schema(name = "checkIn Time", example = "14:00", required = true)
    private LocalTime checkIn;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="HH:mm")
    @Schema(name = "checkOut Time", example = "12:00", required = true)
    private LocalTime checkOut;
}
