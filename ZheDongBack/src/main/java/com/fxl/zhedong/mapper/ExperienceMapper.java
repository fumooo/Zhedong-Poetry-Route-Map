package com.fxl.zhedong.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.Egroup;
import com.fxl.zhedong.entity.Experience;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface ExperienceMapper {
    IPage<Experience> queryExperienceByGroupIdPage(Page<Experience> page, @Param("groupId") Integer groupId);
    int addExperience(Experience experience);//添加诗人城市组信息
    int updateExperience(Experience experience);//添加诗人城市组信息

    int deleteExperience(@Param("id") Integer id);//单个删除
    int delectExperiences(@Param("eids") List<Integer> eids);//批量删除
}
