package com.laytin.HotelApp.models.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laytin.HotelApp.models.abstr.ContactsAbstr;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
public class ContactsMongo extends ContactsAbstr {
    private String phone;
    private String email;
    @Override
    public String toString() {
        return "" + phone;
    }
}
