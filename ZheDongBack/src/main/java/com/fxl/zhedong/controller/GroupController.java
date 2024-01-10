package com.fxl.zhedong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.City;
import com.fxl.zhedong.entity.Egroup;
import com.fxl.zhedong.entity.Scenery;
import com.fxl.zhedong.mapper.GroupMapper;
import com.fxl.zhedong.service.impl.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class GroupController {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupService groupService;
    @GetMapping("/queryGroupByPeopleAndCity")
    public List queryGroupByPeopleAndCity(@RequestParam("name")String name, @RequestParam("cname")String cname){
        List<Egroup> egroups = groupMapper.queryGroupByPeopleAndCity(name,cname);
        return egroups;
    }

    @GetMapping("/queryGroupByPeopleAndCityPage")
    public Map list(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                    @RequestParam String name, @RequestParam String cname) {
        Map<String, Object> EgroupMap = new HashMap<>();
        Page<Egroup> page = new Page<>(pageNum, pageSize);
        IPage<Egroup> data = groupMapper.queryGroupByPeopleAndCityPage(page, name, cname);
        EgroupMap.put("count", data.getTotal());
        EgroupMap.put("data", data.getRecords());
        return EgroupMap;
    }
    @PostMapping("/saveEgroup")
    public int save(@RequestBody Egroup egroup){
        return groupService.save(egroup);
    }

    @DeleteMapping("/Egroup/{id}")
    public int delete(@PathVariable Integer id){
        return groupMapper.deleteEgroup(id);
    }
    @PostMapping("/Egroup/batch")
    public int deleteBatch(@RequestBody List<Integer> eids){
        return groupMapper.delectEgroups(eids);
    }
}
