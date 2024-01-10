package com.fxl.zhedong.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.City;
import com.fxl.zhedong.entity.People;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleMapper {

    List<People> queryAll();//查询所有的诗人内容

    List<People> queryByMohu(@Param("name") String mohu);//模糊查询查询

    People queryPeopleById(@Param("id") int id);//通过诗人的id去查诗人的基本信息

    IPage<People> queryPeoplePage(Page<People> page, @Param("name") String name);//使用分页查询去查询城市信息

    int addPeople(People people);//添加城市信息

    int updatePeople(People people);//修改城市信息

    int deletePeople(@Param("id") Integer id);//单个删除

    int delectPeoples(@Param("eids") List<Integer> eids);//批量删除

    int batchInsert(@Param("list") List<People> list);
// 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入

}
