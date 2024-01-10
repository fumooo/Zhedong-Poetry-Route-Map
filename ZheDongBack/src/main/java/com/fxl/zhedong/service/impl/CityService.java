package com.fxl.zhedong.service.impl;

import com.fxl.zhedong.entity.City;
import com.fxl.zhedong.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CityMapper cityMappper;

    public int save(City city){
        if(city.getId() == null){
            return cityMappper.addCity(city);
        }
        else {
            return cityMappper.updateCity(city);
        }
    }
}
