package com.fxl.zhedong.service.impl;

import com.fxl.zhedong.entity.Experience;
import com.fxl.zhedong.entity.Poem;
import com.fxl.zhedong.mapper.PoemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoemService {
    @Autowired
    private PoemMapper poemMapper;
    public int save(Poem poem){
        if(poem.getId() == null){
            return poemMapper.addPoem(poem);
        }
        else {
            return poemMapper.updatePoem(poem);
        }
    }
}
