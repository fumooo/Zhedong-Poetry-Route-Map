package com.fxl.zhedong.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.Excel.CityExcel.DemoDAOC;
import com.fxl.zhedong.Excel.CityExcel.DemoDataListenerC;
import com.fxl.zhedong.Excel.PeopleExcel.DemoDAOP;
import com.fxl.zhedong.Excel.PeopleExcel.DemoDataListenerP;
import com.fxl.zhedong.entity.City;
import com.fxl.zhedong.entity.People;
import com.fxl.zhedong.mapper.CityMapper;
import com.fxl.zhedong.service.impl.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class CityController {
    @Autowired
    private CityMapper cityMappper;
    @Autowired
    private CityService cityService;
    @Autowired
    private DemoDAOC demoDAOC;
    @GetMapping("/queryByPeopleId")
    public List queryByPeopleId(@RequestParam("id") int id){
        List<City> cities = cityMappper.queryByPeopleId(id);
        return cities;
    }

    @GetMapping("/queryLineByPeopleId")
    public List queryLineByPeopleId(@RequestParam("id") int id){
        List<City> cities = cityMappper.queryLineByPeopleId(id);
        return cities;
    }

    @GetMapping("/queryCityPoemNumber")
    public List queryCityPoemNumber(){
        List<City> cities = cityMappper.queryCityPoemNumber();
        return cities;
    }

    @GetMapping("/queryProvince")
    public List queryProvince(){
        List<City> cities = cityMappper.queryProvince();
        return cities;
    }
    @GetMapping("/queryCity")
    public List queryCity(){
        List<City> cities = cityMappper.queryCity();
        return cities;
    }
    @GetMapping("/queryCityPage")
    public Map list(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                    @RequestParam String cname,@RequestParam String province) {
        Map<String, Object> CityMap = new HashMap<>();
        Page<City> page = new Page<>(pageNum, pageSize);
        IPage<City> data = cityMappper.queryCityPage(page, cname, province);
        CityMap.put("count", data.getTotal());
        CityMap.put("data", data.getRecords());
        return CityMap;
    }

    @PostMapping("/saveCity")
    public int save(@RequestBody City city){
        System.out.println(city);
        return cityService.save(city);
    }

    @DeleteMapping("/City/{id}")
    public int delete(@PathVariable Integer id){
        return cityMappper.delectCity(id);
    }

    @PostMapping("/City/batch")
    public int deleteBatch(@RequestBody List<Integer> eids){
        return cityMappper.delectCitys(eids);
    }

    @GetMapping("/Province")
    public List Province(){
        List<City> cities = cityMappper.queryAllProvince();
        return cities;
    }
    @GetMapping("/City")
    public List City(){
        List<City> cities = cityMappper.queryAllCity();
        return cities;
    }
    @PostMapping("/City/upload")
    public String upload (MultipartFile file) throws IOException {
        System.out.println(file);
        EasyExcel.read(file.getInputStream(), City.class, new DemoDataListenerC(demoDAOC)).sheet().doRead();
        return "success";
    }

}
