package com.fxl.zhedong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.Experience;
import com.fxl.zhedong.entity.Poem;
import com.fxl.zhedong.mapper.ExperienceMapper;
import com.fxl.zhedong.mapper.PoemMapper;
import com.fxl.zhedong.service.impl.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PoemController {
    @Autowired
    private PoemMapper poemMapper;
    @Autowired
    private PoemService poemService;
    @GetMapping("/queryPoemByExperienceIdPage")
    public Map list(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                    @RequestParam Integer experienceId) {
        Map<String, Object> PoemMap = new HashMap<>();
        Page<Poem> page = new Page<>(pageNum, pageSize);
        IPage<Poem> data = poemMapper.queryPoemByExperienceIdPage(page, experienceId);
        PoemMap.put("count", data.getTotal());
        PoemMap.put("data", data.getRecords());
        return PoemMap;
    }
    @PostMapping("/savePoem")
    public int save(@RequestBody Poem poem){
        return poemService.save(poem);
    }
    @DeleteMapping("/Poem/{id}")
    public int delete(@PathVariable Integer id){
        return poemMapper.deletePoem(id);
    }
    @PostMapping("/Poem/batch")
    public int deleteBatch(@RequestBody List<Integer> eids){
        return poemMapper.delectPoems(eids);
    }
}
