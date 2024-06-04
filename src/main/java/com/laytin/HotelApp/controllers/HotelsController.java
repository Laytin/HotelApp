package com.laytin.HotelApp.controllers;

import com.laytin.HotelApp.dto.HotelSimpleDTO;
import com.laytin.HotelApp.dto.HotelDTO;
import com.laytin.HotelApp.dto.RegHotelDTO;
import com.laytin.HotelApp.utils.exception.ErrorBuilder;
import com.laytin.HotelApp.service.HotelService;
import com.laytin.HotelApp.utils.exception.DefaultErrorException;
import com.laytin.HotelApp.utils.exception.DefaultErrorResponce;
import com.laytin.HotelApp.utils.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HotelsController implements IHotelsController{
    private final HotelService hotelService;
    private final HotelMapper mapper;
    @Autowired
    public HotelsController(HotelService hotelService, HotelMapper mapper) {
        this.hotelService = hotelService;
        this.mapper = mapper;
    }
    @Override
    public List<HotelSimpleDTO> getHotelList(){
        return mapper.mapHotelListToSimpleList(hotelService.getHotels());
    }
    @Override
    public HotelDTO getHotelById(int id){
        return mapper.mapHotelToDTO(hotelService.getHotelById(id));
    }
    @Override
    public List<HotelSimpleDTO> searchHotelsBySmth(Optional<String> name, Optional<String> brand, Optional<String> city, Optional<String> country, Optional<String[]> amenities){
        return mapper.mapHotelListToSimpleList(hotelService.searchHotels(name,brand,city,country,amenities));
    }
    @Override
    public HotelSimpleDTO createNewHotel(RegHotelDTO hotelDTO, BindingResult result){
        if(result.hasErrors())
            ErrorBuilder.buildErrorMessageForClient(result);
        return mapper.mapHotelToSimple(hotelService.createNewHotel(mapper.mapDTOToHotel(hotelDTO)));
    }
    @Override
    public HotelDTO changeAmenities(int id,String[] amenities){
        return mapper.mapHotelToDTO(hotelService.addAmenities(id, amenities));
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    private ResponseEntity<DefaultErrorResponce> handleException(DefaultErrorException e) {
        DefaultErrorResponce response = new DefaultErrorResponce(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
