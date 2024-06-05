package com.laytin.HotelApp.utils.mapper;

import com.laytin.HotelApp.dto.HotelDTO;
import com.laytin.HotelApp.dto.HotelSimpleDTO;
import com.laytin.HotelApp.dto.RegHotelDTO;
import com.laytin.HotelApp.models.Hotel;
import com.laytin.HotelApp.models.abstr.HotelAbstr;

import java.util.List;

public interface IHotelMapper {
    <T extends HotelAbstr> HotelSimpleDTO mapHotelToSimple(T h);

    List<HotelSimpleDTO> mapHotelListToSimpleList(List<? extends HotelAbstr> h);

    <T extends HotelAbstr> T mapDTOToHotel(RegHotelDTO hotelDTO);

    <T extends HotelAbstr> HotelDTO mapHotelToDTO(T hotelById);
}
