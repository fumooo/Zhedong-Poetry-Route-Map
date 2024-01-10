package com.fxl.zhedong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxl.zhedong.entity.City;
import com.fxl.zhedong.entity.People;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CityMapper extends BaseMapper<City> {
   List<City> queryByPeopleId(@Param("id") int id);//查询覆盖物点的基本信息
   List<City> queryLineByPeopleId(@Param("id") int id);//查询覆盖物曲线的基本信息 只需要城市经纬度
   List<City> queryCityPoemNumber();//查询每个城市都存在都唐诗的数量
   List<City> queryProvince();//查询省份不重复
   List<City> queryCity();//查询城市不重复
   IPage<City> queryCityPage(Page<City> page, @Param("cname") String cname,
                             @Param("province") String province);//使用分页查询去查询城市信息 同时两个模糊查询
   int addCity(City city);//添加城市信息
   int updateCity(City city);//修改城市信息
   int delectCity(@Param("id") Integer id);//删除城市信息
   int delectCitys(@Param("eids") List<Integer> eids);//批量删除城市信息
   List<City> queryAllProvince();//查询所有省份信息
   List<City> queryAllCity();//通过省份查询省份所对应的城市信息
   int batchInsertC(@Param("list") List<City> list);
}
