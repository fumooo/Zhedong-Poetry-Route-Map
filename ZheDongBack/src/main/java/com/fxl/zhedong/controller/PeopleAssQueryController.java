package com.fxl.zhedong.controller;
import com.fxl.zhedong.entity.PeopleAssQuery.PeopleAssQuery;
import com.fxl.zhedong.mapper.PeopleAssQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class PeopleAssQueryController {
    @Autowired
    private PeopleAssQueryMapper peopleAssQueryMapper;
    @GetMapping("/queryInformation")
    public List queryInformationByCityAndPeopleName(@RequestParam("cname") String CityName,@RequestParam("pname") String PeopleName){
        List<PeopleAssQuery> peopleAssQuery = peopleAssQueryMapper.queryInformationByCityAndPeopleName(CityName,PeopleName);
        return peopleAssQuery;
    }

    @GetMapping("/queryInformation1")
    public List queryInformationByCityAndPeopleNameByStep(@RequestParam("cname") String CityName,@RequestParam("pname") String PeopleName){
        List<PeopleAssQuery> peopleAssQuery = peopleAssQueryMapper.queryInformationByCityAndPeopleNameByStepOne(CityName,PeopleName);
        return peopleAssQuery;
    }
}
