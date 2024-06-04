package com.laytin.HotelApp.service;

import com.laytin.HotelApp.models.Hotel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IHotelService {
    Hotel getHotelById(int i);

    //1+n solving
    List<Hotel> getHotels();

    List<Hotel> searchHotels(Optional<String> name, Optional<String> brand, Optional<String> city, Optional<String> country, Optional<String[]> amenities);

    @Transactional
    Hotel createNewHotel(Hotel o);

    @Transactional
    Hotel addAmenities(int id, String[] amenities);
}
