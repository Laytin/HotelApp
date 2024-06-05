package com.laytin.HotelApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laytin.HotelApp.models.abstr.ContactsAbstr;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Data
@Entity
@Table(name = "contacts")
@NoArgsConstructor
public class Contacts extends ContactsAbstr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @OneToOne
    @JoinColumn(name = "hotel_id")
    @JsonIgnore
    @ToString.Exclude
    private Hotel hotel;
    private String phone;
    private String email;

    @Override
    public String toString() {
        return "" + phone;
    }
}
