package com.laytin.HotelApp.controllers;

import com.laytin.HotelApp.service.HistogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HistogramController implements IHistogramController{
    private final HistogramService histogramService;
    @Autowired
    public HistogramController(HistogramService histogramService) {
        this.histogramService = histogramService;
    }
    @Override
    public Map<String, Long> getHistogram(String type){
        return histogramService.getHistogramByType(type);
    }
}
