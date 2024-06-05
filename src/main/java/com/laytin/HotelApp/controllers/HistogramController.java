package com.laytin.HotelApp.controllers;

import com.laytin.HotelApp.service.HistogramService;
import com.laytin.HotelApp.service.HistogramServiceMongo;
import com.laytin.HotelApp.service.IHistogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HistogramController implements IHistogramController{
    private final IHistogramService histogramService;
    @Autowired
    public HistogramController(IHistogramService histogramService) {
        this.histogramService = histogramService;
    }

    @Override
    public Map<String, Long> getHistogram(String type){
        return histogramService.getHistogramByType(type);
    }
}
