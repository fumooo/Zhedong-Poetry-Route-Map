package com.fxl.zhedong.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.Experience;
import com.fxl.zhedong.entity.Poem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PoemMapper {
    IPage<Poem> queryPoemByExperienceIdPage(Page<Poem> page, @Param("experienceId") Integer experienceId);
    int addPoem(Poem poem);//添加诗人城市组信息
    int updatePoem(Poem poem);//添加诗人城市组信息

    int deletePoem(@Param("id") Integer id);//单个删除
    int delectPoems(@Param("eids") List<Integer> eids);//批量删除
}
