package com.laytin.HotelApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Tag(name = "Contacts DTO", description = "Contacts DTO")
@Data
public class ContactsDTO {
    @NotNull
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    @Schema(name = "phone number", example = "+37529266556", required = true)
    private String phone;
    @NotNull
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Schema(name = "email", example = "smth@gmail.com", required = true)
    private String email;
}
