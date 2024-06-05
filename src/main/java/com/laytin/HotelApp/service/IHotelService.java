package com.laytin.HotelApp.service;

import com.laytin.HotelApp.models.Hotel;
import com.laytin.HotelApp.models.abstr.HotelAbstr;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IHotelService {
    <T extends HotelAbstr> T getHotelById(int i);

    //1+n solving
    List<? extends HotelAbstr> getHotels();

    List<? extends HotelAbstr> searchHotels(Optional<String> name, Optional<String> brand, Optional<String> city, Optional<String> country, Optional<String[]> amenities);

    @Transactional
    <T extends HotelAbstr> T createNewHotel(T o);

    @Transactional
    <T extends HotelAbstr> T addAmenities(int id, String[] amenities);
}
