package com.fxl.zhedong.service.impl;

import com.fxl.zhedong.entity.People;
import com.fxl.zhedong.mapper.PeopleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleService {
    @Autowired
    private PeopleMapper peopleMapper;
    public int save(People people){
        if(people.getId() == null){
            return peopleMapper.addPeople(people);
        }
        else {
            return peopleMapper.updatePeople(people);
        }
    }
}
