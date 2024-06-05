package com.laytin.HotelApp.utils.mapper;

import com.laytin.HotelApp.dto.HotelDTO;
import com.laytin.HotelApp.dto.HotelSimpleDTO;
import com.laytin.HotelApp.dto.RegHotelDTO;
import com.laytin.HotelApp.models.mongo.HotelMongo;
import com.laytin.HotelApp.models.abstr.HotelAbstr;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Profile("nosql")
@Component
public class HotelMapperMongo implements IHotelMapper{
    private final ModelMapper mapper;
    @Autowired
    public HotelMapperMongo(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T extends HotelAbstr> HotelSimpleDTO mapHotelToSimple(T a) {
        HotelMongo h = (HotelMongo) a;
        HotelSimpleDTO hs = new HotelSimpleDTO();
        hs.setId(h.getId());
        hs.setName(h.getName());
        hs.setDescription(h.getDescription());
        hs.setAddress(h.getAddress().toString());
        hs.setContacts(h.getContacts().toString());
        return hs;
    }

    @Override
    public List<HotelSimpleDTO> mapHotelListToSimpleList(List<? extends HotelAbstr> h) {
        List<HotelSimpleDTO> hotelSimpleDTOList = new ArrayList<>();
        ((List<HotelMongo>)h).stream().forEach(f-> hotelSimpleDTOList.add(mapHotelToSimple(f)));
        return hotelSimpleDTOList;
    }

    @Override
    public HotelMongo mapDTOToHotel(RegHotelDTO hotelDTO) {
        return mapper.map(hotelDTO,HotelMongo.class);
    }

    @Override
    public <T extends HotelAbstr> HotelDTO mapHotelToDTO(T hotelById1) {
        if(hotelById1==null) return null;
        HotelMongo hotelById = (HotelMongo) hotelById1;
        HotelDTO res = mapper.map(hotelById, HotelDTO.class);
        res.setAmenities(new ArrayList<>());
        if(hotelById.getAmenities()!=null)
            hotelById.getAmenities().forEach(f-> res.getAmenities().add(f.getTitle()));
        return res;
    }
}
