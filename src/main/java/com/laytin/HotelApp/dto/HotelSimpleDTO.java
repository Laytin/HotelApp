package com.laytin.HotelApp.dto;

import com.laytin.HotelApp.models.Address;
import com.laytin.HotelApp.models.Contacts;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
@Tag(name = "HotelSimpleDTO", description = "short info about hotel")
@Data
public class HotelSimpleDTO {
    @Schema(name = "hotel id (dev info)", example = "9")
    private int id;
    @Schema(name = "hotel name", example = "Hilton Grand Hotel")
    private String name;
    @Schema(name = "hotel description, will be displayed as short info", example = "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor")
    private String description;
    @Schema(name = "Hotel Adress in one line", example = "9 Pobediteley Avenue, Minsk, 220004, Belarus")
    private String address;
    @Schema(name = "Hotel phone", example = "+375 17 309-80-00")
    private String contacts;
}
