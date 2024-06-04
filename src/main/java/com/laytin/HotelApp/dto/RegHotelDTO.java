package com.laytin.HotelApp.dto;

import com.laytin.HotelApp.models.AmenityEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
@Tag(name = "RegHotelDTO", description = "using for register new hotel")
@Data
public class RegHotelDTO {
    @NotNull
    @Size(min = 1, message = "too short hotel name")
    @Schema(name = "hotel name", example = "Hilton Grand Hotel", required = true)
    private String name;
    @NotNull
    @Size(min = 1, max = 500, message = "lenght wrong (min =1, max = 500)")
    @Schema(name = "hotel description, will be displayed as short info", example = "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor", required = true)
    private String description;
    @NotNull
    @Size(min = 1, message = "too short brand name")
    @Schema(name = "brand name", example = "Hilton INC", required = true)
    private String brand;
    @NotNull
    @Valid
    @Schema(name = "Hotel address",  required = true)
    private AddressDTO address;
    @NotNull
    @Valid
    @Schema(name = "Hotel Contacts",  required = true)
    private ContactsDTO contacts;
    @NotNull
    @Valid
    @Schema(name = "Arrival Time",  required = true)
    private ArrivalTimeDTO arrivalTime;
}
