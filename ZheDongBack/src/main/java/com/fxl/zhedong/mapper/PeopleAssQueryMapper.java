package com.fxl.zhedong.mapper;

import com.fxl.zhedong.entity.Experience;
import com.fxl.zhedong.entity.PeopleAssQuery.PeopleAssExperience;
import com.fxl.zhedong.entity.PeopleAssQuery.PeopleAssQuery;
import com.fxl.zhedong.entity.Poem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PeopleAssQueryMapper {
    //通过诗人姓名和其访问的城市姓名去查询其所有的信息
    List<PeopleAssQuery> queryInformationByCityAndPeopleName(@Param("Cityname") String Cityname, @Param("PeopleName") String PeopleName);
    //通过分布查询去查询诗人姓名和其访问的城市姓名去查询其所有的信息 第一步
    List<PeopleAssQuery> queryInformationByCityAndPeopleNameByStepOne(@Param("Cityname") String Cityname, @Param("PeopleName") String PeopleName);
    //通过分布查询egroup去查询experience
    List<PeopleAssExperience> queryInformationByCityAndPeopleNameByStepTwo(@Param("groupId") Integer groupId);
    //通过分布查询experience去查询其经历写的poem
    List<Poem> queryInformationByCityAndPeopleNameByStepThree(@Param("experienceId") Integer experienceId);
}
