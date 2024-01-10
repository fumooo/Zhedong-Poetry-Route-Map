package com.fxl.zhedong.Excel.CityExcel;

import com.fxl.zhedong.entity.City;
import com.fxl.zhedong.entity.People;
import com.fxl.zhedong.mapper.CityMapper;
import com.fxl.zhedong.mapper.PeopleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 假设这个是你的DAO存储。当然还要这个类让spring管理，当然你不用需要存储，也不需要这个类。
 **/
@Component
public class DemoDAOC {
    @Autowired
    private CityMapper cityMapper;
    public void save(List<City> list) {

        System.out.println(list);
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
//        peopleMapper.batchInsert(list);
        cityMapper.batchInsertC(list);
        System.out.println("进行一次批量增加宝贝！!");
    }
}