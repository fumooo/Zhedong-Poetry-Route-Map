package com.fxl.zhedong.service.impl;

import com.fxl.zhedong.entity.Egroup;
import com.fxl.zhedong.entity.Experience;
import com.fxl.zhedong.mapper.ExperienceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {
    @Autowired
    private ExperienceMapper experienceMapper;
    public int save(Experience experience){
        if(experience.getId() == null){
            return experienceMapper.addExperience(experience);
        }
        else {
            return experienceMapper.updateExperience(experience);
        }
    }
}
