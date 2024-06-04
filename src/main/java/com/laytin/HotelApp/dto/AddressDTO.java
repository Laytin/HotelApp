package com.laytin.HotelApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Tag(name = "AddressDTO", description = "Address DTO")
@Data
public class AddressDTO {
    @NotNull
    @Schema(name = "House number", example = "9", required = true)
    private String houseNumber;
    @NotNull
    @Size(min = 1, message = "too short street name")
    @Schema(name = "street name", example = "Pobediteley", required = true)
    private String street;
    @NotNull
    @Size(min = 1, message = "too short city name")
    @Schema(name = "city name", example = "Minsk", required = true)
    private String city;
    @NotNull
    @Size(min = 1, message = "too short country name")
    @Schema(name = "country name", example = "Belarus", required = true)
    private String country;
    @NotNull
    @Size(min = 5, max = 7, message = "wrong format")
    @Schema(name = "postCode", example = "222333", required = true)
    private String postCode;
}
