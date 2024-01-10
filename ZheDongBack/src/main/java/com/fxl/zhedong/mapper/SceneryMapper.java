package com.fxl.zhedong.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.People;
import com.fxl.zhedong.entity.Scenery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SceneryMapper {
    List<Scenery> querySceneryPeopleByPeopleId(@Param("id") int id);//查询诗人姓名，简介，以及他们游玩的景点
    List<Scenery> querySceneryByPeopleId(@Param("id") int id);//通过查询诗人的id来获得其访问过的景点信息
    IPage<Scenery> querySceneryByPeopleNamePage(Page<Scenery> page, @Param("name") String name);//使用分页查询 通过诗人的姓名去查询
    int addScenery(Scenery scenery);//添加景点信息
    int updateScenery(Scenery scenery);//修改景点信息
    int deleteScenery(@Param("id") Integer id);//单个删除
    int delectScenerys(@Param("eids") List<Integer> eids);//批量删除

}
