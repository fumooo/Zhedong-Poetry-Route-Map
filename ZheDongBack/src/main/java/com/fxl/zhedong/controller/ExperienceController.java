package com.fxl.zhedong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.Egroup;
import com.fxl.zhedong.entity.Experience;
import com.fxl.zhedong.mapper.ExperienceMapper;
import com.fxl.zhedong.service.impl.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ExperienceController {
    @Autowired
    private ExperienceMapper experienceMapper;
    @Autowired
    private ExperienceService experienceService;

    @GetMapping("/queryExperienceByGroupIdPage")
    public Map list(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                    @RequestParam Integer groupId) {
        Map<String, Object> ExperienceMap = new HashMap<>();
        Page<Experience> page = new Page<>(pageNum, pageSize);
        IPage<Experience> data = experienceMapper.queryExperienceByGroupIdPage(page, groupId);
        ExperienceMap.put("count", data.getTotal());
        ExperienceMap.put("data", data.getRecords());
        return ExperienceMap;
    }

    @PostMapping("/saveExperience")
    public int save(@RequestBody Experience experience){
        return experienceService.save(experience);
    }
    @DeleteMapping("/Experience/{id}")
    public int delete(@PathVariable Integer id){
        return experienceMapper.deleteExperience(id);
    }
    @PostMapping("/Experience/batch")
    public int deleteBatch(@RequestBody List<Integer> eids){
        return experienceMapper.delectExperiences(eids);
    }
}
