package com.laytin.HotelApp.service;

import com.laytin.HotelApp.dao.HistogramDAO;
import com.laytin.HotelApp.repository.AddressRepository;
import com.laytin.HotelApp.repository.ContactsRepository;
import com.laytin.HotelApp.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
@Profile("sql")
@Service
@Transactional(readOnly = true)
public class HistogramService implements IHistogramService{
    private final HistogramDAO histogramDAO;
    @Autowired
    public HistogramService(HotelRepository hotelRepository, AddressRepository addressRepository, ContactsRepository contactsRepository, HistogramDAO histogramDAO) {
        this.histogramDAO = histogramDAO;
    }
    @Override
    public Map<String,Long> getHistogramByType(String type) {
        return histogramDAO.counterBy(type);
    }
}
