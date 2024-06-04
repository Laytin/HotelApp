package com.laytin.HotelApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.laytin.HotelApp.models.AmenityEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.List;
@Tag(name = "HotelDTO", description = "full info about hotel")
@Data
public class HotelDTO {
    @Schema(name = "hotel id (dev info)", example = "9")
    private int id;
    @Schema(name = "hotel name", example = "Hilton Grand Hotel")
    private String name;
    @Schema(name = "brand name", example = "Hilton INC")
    private String brand;
    @Schema(name = "Hotel Adress")
    private AddressDTO address;
    @Schema(name = "Hotel Contacts")
    private ContactsDTO contacts;
    @Schema(name = "Arrival Time")
    @JsonFormat(pattern="HH-mm")
    private ArrivalTimeDTO arrivalTime;
    @Schema(name = "Hotel Amenities")
    private List<String> amenities;

}
