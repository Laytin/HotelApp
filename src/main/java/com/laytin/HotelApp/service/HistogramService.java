package com.laytin.HotelApp.service;

import com.laytin.HotelApp.dao.HistogramDAO;
import com.laytin.HotelApp.models.Hotel;
import com.laytin.HotelApp.repository.AddressRepository;
import com.laytin.HotelApp.repository.ContactsRepository;
import com.laytin.HotelApp.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class HistogramService {
    private final HotelRepository hotelRepository;
    private final AddressRepository addressRepository;
    private final ContactsRepository contactsRepository;
    private final HistogramDAO histogramDAO;
    @Autowired
    public HistogramService(HotelRepository hotelRepository, AddressRepository addressRepository, ContactsRepository contactsRepository, HistogramDAO histogramDAO) {
        this.hotelRepository = hotelRepository;
        this.addressRepository = addressRepository;
        this.contactsRepository = contactsRepository;
        this.histogramDAO = histogramDAO;
    }
    public Map<String,Long> getHistogramByType(String type) {
        return histogramDAO.counterBy(type);
    }
}
