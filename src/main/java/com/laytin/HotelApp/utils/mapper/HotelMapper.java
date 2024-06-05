package com.laytin.HotelApp.utils.mapper;

import com.laytin.HotelApp.dto.HotelSimpleDTO;
import com.laytin.HotelApp.dto.HotelDTO;
import com.laytin.HotelApp.dto.RegHotelDTO;
import com.laytin.HotelApp.models.Hotel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class HotelMapper {
    private final ModelMapper mapper;
    @Autowired
    public HotelMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public HotelSimpleDTO mapHotelToSimple(Hotel h){
        HotelSimpleDTO hs = new HotelSimpleDTO();
        hs.setId(h.getId());
        hs.setName(h.getName());
        hs.setDescription(h.getDescription());
        hs.setAddress(h.getAddress().toString());
        hs.setContacts(h.getContacts().toString());
        return hs;
    }
    public List<HotelSimpleDTO> mapHotelListToSimpleList(List<Hotel> h){
        List<HotelSimpleDTO> hotelSimpleDTOList = new ArrayList<>();
        h.stream().forEach(f-> hotelSimpleDTOList.add(mapHotelToSimple(f)));
        return hotelSimpleDTOList;
    }

    public Hotel mapDTOToHotel(RegHotelDTO hotelDTO) {
        return mapper.map(hotelDTO,Hotel.class);
    }

    public HotelDTO mapHotelToDTO(Hotel hotelById) {
        if(hotelById==null) return null;
        HotelDTO res = mapper.map(hotelById, HotelDTO.class);
        res.setAmenities(new ArrayList<>());
        hotelById.getAmenities().forEach(f-> res.getAmenities().add(f.getTitle()));
        return res;
    }
}
