package com.fxl.zhedong.service.impl;

import com.fxl.zhedong.entity.Egroup;
import com.fxl.zhedong.entity.Scenery;
import com.fxl.zhedong.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    private GroupMapper groupMapper;
    public int save(Egroup egroup){
        if(egroup.getId() == null){
            return groupMapper.addEgroup(egroup);
        }
        else {
            return groupMapper.updateEgroup(egroup);
        }
    }
}
