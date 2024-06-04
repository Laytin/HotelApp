package com.laytin.HotelApp.service;

import java.util.Map;

public interface IHistogramService {
    Map<String,Long> getHistogramByType(String type);
}
