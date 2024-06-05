package com.laytin.HotelApp.models.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laytin.HotelApp.models.abstr.AddressAbstr;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
public class AddressMongo extends AddressAbstr {
    private String houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;

    @Override
    public String toString() {
        return "" + houseNumber + " " + street + ", "+city + ", "+postCode + ", "+ country;
    }
}
