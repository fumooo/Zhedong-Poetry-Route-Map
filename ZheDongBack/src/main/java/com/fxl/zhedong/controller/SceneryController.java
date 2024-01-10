package com.fxl.zhedong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.Scenery;
import com.fxl.zhedong.mapper.SceneryMapper;
import com.fxl.zhedong.service.impl.SceneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class SceneryController {
    @Autowired
    private SceneryMapper sceneryMapper;
    @Autowired
    private SceneryService sceneryService;
//    @GetMapping("/querySceneryPeopleByPeopleId")
//    public List querySceneryPeopleByPeopleId(@RequestParam("id") int id){
//        List<Scenery> scenery = sceneryMapper.querySceneryPeopleByPeopleId(id);
//        return scenery;
//    }

    @GetMapping ("/querySceneryById")
    public List querySceneryByPeopleId(@RequestParam("id") int id){
        List<Scenery> scenery = sceneryMapper.querySceneryByPeopleId(id);
        return scenery;
    }

    @GetMapping("/querySceneryByPeopleNamePage")
    public Map list(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String name) {
        Map<String, Object> SceneryMap = new HashMap<>();
        Page<Scenery> page = new Page<>(pageNum, pageSize);
        IPage<Scenery> data = sceneryMapper.querySceneryByPeopleNamePage(page,name);
        SceneryMap.put("count", data.getTotal());
        SceneryMap.put("data", data.getRecords());
        return SceneryMap;
    }

    @PostMapping("/saveScenery")
    public int save(@RequestBody Scenery scenery){
        return sceneryService.save(scenery);
    }

    @DeleteMapping("/Scenery/{id}")
    public int delete(@PathVariable Integer id){
        return sceneryMapper.deleteScenery(id);
    }
    @PostMapping("/Scenery/batch")
    public int deleteBatch(@RequestBody List<Integer> eids){
        return sceneryMapper.delectScenerys(eids);
    }

}
