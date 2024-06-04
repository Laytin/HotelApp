package com.laytin.HotelApp;

import com.laytin.HotelApp.dto.HotelDTO;
import com.laytin.HotelApp.dto.RegHotelDTO;
import com.laytin.HotelApp.models.Hotel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelAppApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Hotel, HotelDTO>() {
			@Override
			protected void configure() {
				skip(destination.getAmenities());
			}
		});
		return modelMapper;
	}
}
