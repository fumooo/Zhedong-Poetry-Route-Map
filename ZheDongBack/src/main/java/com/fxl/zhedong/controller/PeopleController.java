package com.fxl.zhedong.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.Excel.PeopleExcel.DemoDAOP;
import com.fxl.zhedong.Excel.PeopleExcel.DemoDataListenerP;
import com.fxl.zhedong.entity.People;
import com.fxl.zhedong.mapper.PeopleMapper;
import com.fxl.zhedong.service.impl.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PeopleController {
    @Autowired
    private PeopleMapper peopleMapper;
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private DemoDAOP demoDAO;
    @GetMapping("/people")
    public List queryAll(){
        List<People> people = peopleMapper.queryAll();
        return people;
    }

    @GetMapping("/people/mohu")//通过诗人姓名模糊查询
    public List queryByMohu(@RequestParam("name") String mohu){
        List<People> people = peopleMapper.queryByMohu(mohu);
        return people;
    }

    @GetMapping("/peopleById")//查询诗人姓名和介绍
    public People queryPeopleById(@RequestParam("id") int id){
        People people = peopleMapper.queryPeopleById(id);
        return people;
    }

    @GetMapping("/queryPeoplePage")
    public Map list(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String name) {
        Map<String, Object> PeopleMap = new HashMap<>();
        Page<People> page = new Page<>(pageNum, pageSize);
        IPage<People> data = peopleMapper.queryPeoplePage(page,name);
        PeopleMap.put("count", data.getTotal());
        PeopleMap.put("data", data.getRecords());
        return PeopleMap;
    }

    @PostMapping("/savePeople")
    public int save(@RequestBody People people){
        return peopleService.save(people);
    }

    @DeleteMapping("/People/{id}")
    public int delete(@PathVariable Integer id){
        return peopleMapper.deletePeople(id);
    }
    @PostMapping("/People/batch")
    public int deleteBatch(@RequestBody List<Integer> eids){
        return peopleMapper.delectPeoples(eids);
    }

    @PostMapping("/People/upload")
    @ResponseBody
    public String upload (MultipartFile file) throws IOException {
        System.out.println(file);
        EasyExcel.read(file.getInputStream(), People.class, new DemoDataListenerP(demoDAO)).sheet().doRead();
        return "success";
    }


}
