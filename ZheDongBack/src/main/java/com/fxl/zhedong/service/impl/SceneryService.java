package com.fxl.zhedong.service.impl;

import com.fxl.zhedong.entity.City;
import com.fxl.zhedong.entity.Scenery;
import com.fxl.zhedong.mapper.SceneryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SceneryService {
    @Autowired
    private SceneryMapper sceneryMapper;
    public int save(Scenery scenery){
        if(scenery.getId() == null){
            return sceneryMapper.addScenery(scenery);
        }
        else {
            return sceneryMapper.updateScenery(scenery);
        }
    }
}
