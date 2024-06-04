package com.laytin.HotelApp.service;

import com.laytin.HotelApp.dao.HotelDAO;
import com.laytin.HotelApp.models.AmenityEnum;
import com.laytin.HotelApp.models.Hotel;
import com.laytin.HotelApp.repository.AddressRepository;
import com.laytin.HotelApp.repository.ArrivalTimeRepository;
import com.laytin.HotelApp.repository.ContactsRepository;
import com.laytin.HotelApp.repository.HotelRepository;
import com.laytin.HotelApp.utils.exception.ErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HotelService {
    private final HotelRepository hotelRepository;
    private final AddressRepository addressRepository;
    private final ContactsRepository contactsRepository;
    private final ArrivalTimeRepository arrivalTimeRepository;
    private final HotelDAO hotelDAO;
    @Autowired
    public HotelService(HotelRepository hotelRepository, AddressRepository addressRepository, ContactsRepository contactsRepository, ArrivalTimeRepository arrivalTimeRepository, HotelDAO hotelDAO) {
        this.hotelRepository = hotelRepository;
        this.addressRepository = addressRepository;
        this.contactsRepository = contactsRepository;
        this.arrivalTimeRepository = arrivalTimeRepository;
        this.hotelDAO = hotelDAO;
    }
    public Hotel getHotelById(int i ){
        Optional<Hotel> g = hotelRepository.findById(i);
        if(!g.isPresent())
            ErrorBuilder.buildErrorMessageForClient("Hotel not found");
        return g.get();
    }
    //1+n solving
    public List<Hotel> getHotels() {
        return hotelRepository.findAllHotels();
    }

    public List<Hotel> searchHotels(Optional<String> name, Optional<String> brand, Optional<String> city, Optional<String> country, Optional<String> amenities) {
        return hotelDAO.search(name, brand, city, country, amenities);
    }
    @Transactional
    public Hotel createNewHotel(Hotel o) {
        Hotel res = hotelRepository.save(o);
        o.getAddress().setHotel(res);
        o.getContacts().setHotel(res);
        o.getArrivalTime().setHotel(res);
        addressRepository.save(o.getAddress());
        contactsRepository.save(o.getContacts());
        arrivalTimeRepository.save(o.getArrivalTime());
        return res;
    }
    @Transactional
    public Hotel addAmenities(int id, String[] amenities) {
        List<AmenityEnum> am  = new ArrayList<>();
        Arrays.stream(amenities).toList().forEach(f->{
            AmenityEnum e = AmenityEnum.getEnumByString(f);
            if(e!=null)
                am.add(e);
        });
        Optional<Hotel> res = hotelRepository.findById(id);
        if(!res.isPresent())
            ErrorBuilder.buildErrorMessageForClient("Hotel with this id not found");
        res.get().setAmenities(am);
        hotelRepository.save(res.get());
        return res.get();
    }
}
