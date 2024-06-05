package com.laytin.HotelApp.service;

import com.laytin.HotelApp.dao.HistogramDAOMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Profile("nosql")
@Service
@Transactional(readOnly = true)
public class HistogramServiceMongo implements IHistogramService {
    private final HistogramDAOMongo histogramDAOMongo;

    @Autowired
    public HistogramServiceMongo(HistogramDAOMongo histogramDAOMongo) {
        this.histogramDAOMongo = histogramDAOMongo;
    }

    @Override
    public Map<String, Long> getHistogramByType(String type) {
        return histogramDAOMongo.counterBy(type);
    }
}
