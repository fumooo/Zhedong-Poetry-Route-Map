package com.fxl.zhedong.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.City;
import com.fxl.zhedong.entity.Egroup;
import com.fxl.zhedong.entity.Scenery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMapper {
    List<Egroup> queryById();
    List<Egroup> queryGroupByPeopleAndCity(@Param("name")String name,@Param("cname")String cname);

    IPage<Egroup> queryGroupByPeopleAndCityPage(Page<Egroup> page, @Param("name") String name,
                              @Param("cname") String cname);
    int addEgroup(Egroup egroup);//添加诗人城市组信息
    int updateEgroup(Egroup egroup);//添加诗人城市组信息

    int deleteEgroup(@Param("id") Integer id);//单个删除
    int delectEgroups(@Param("eids") List<Integer> eids);//批量删除
}
