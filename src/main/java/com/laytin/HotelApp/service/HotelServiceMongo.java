package com.laytin.HotelApp.service;

import com.laytin.HotelApp.dao.HotelDAO;
import com.laytin.HotelApp.dao.HotelDAOMongo;
import com.laytin.HotelApp.models.AmenityEnum;
import com.laytin.HotelApp.models.Hotel;
import com.laytin.HotelApp.models.mongo.HotelMongo;
import com.laytin.HotelApp.models.mongo.MongoCounter;
import com.laytin.HotelApp.models.abstr.HotelAbstr;
import com.laytin.HotelApp.repository.*;
import com.laytin.HotelApp.utils.exception.ErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Profile("nosql")
@Service
@Transactional(readOnly = true)
public class HotelServiceMongo implements IHotelService{
    private final HotelRepositoryMongo hotelRepository;
    private final HotelDAOMongo hotelDAO;
    @Autowired
    public HotelServiceMongo(HotelRepositoryMongo hotelRepository, HotelDAOMongo hotelDAO) {
        this.hotelRepository = hotelRepository;
        this.hotelDAO = hotelDAO;
    }
    @Override
    public HotelMongo getHotelById(int i) {
        Optional<HotelMongo> g = hotelRepository.findById(i);
        if(!g.isPresent()) return null;
        //ErrorBuilder.buildErrorMessageForClient("Hotel not found");
        return g.get();
    }

    @Override
    public List<HotelMongo> getHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public List<HotelMongo> searchHotels(Optional<String> name, Optional<String> brand, Optional<String> city, Optional<String> country, Optional<String[]> amenities) {
        return hotelDAO.search(name, brand, city, country, amenities);
    }

    @Override
    public <T extends HotelAbstr> T createNewHotel(T a) {
        HotelMongo o = (HotelMongo) a;
        o.setId(hotelDAO.getNextSequence());
        HotelMongo res = hotelRepository.save(o);
        return (T) res;
    }

    @Override
    public HotelMongo addAmenities(int id, String[] amenities) {
        List<AmenityEnum> am  = new ArrayList<>();
        Arrays.stream(amenities).toList().forEach(f->{
            AmenityEnum e = AmenityEnum.getEnumByString(f);
            if(e!=null)
                am.add(e);
        });
        Optional<HotelMongo> res = hotelRepository.findById(id);
        if(!res.isPresent())
            ErrorBuilder.buildErrorMessageForClient("Hotel with this id not found");
        res.get().setAmenities(am);
        hotelRepository.save(res.get());
        return res.get();
    }


}
