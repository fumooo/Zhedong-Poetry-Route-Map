package com.fxl.zhedong;

import com.alibaba.excel.EasyExcel;
import com.fxl.zhedong.Excel.PeopleExcel.DemoDataListenerP;
import com.fxl.zhedong.entity.People;
import com.fxl.zhedong.mapper.PeopleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import com.fxl.zhedong.config
import java.util.List;

@SpringBootTest
class ZheDongApplicationTests {
    @Autowired
    private PeopleMapper peopleMapper;

    private List<People> data(){
        List<People> people = peopleMapper.queryAll();
        return people;
    }
    @Test
    void ExcelWriteTest() {
//        FileExistsPropertyDefiner TestFileUtil = null;
        String fileName = "/Users/fuxiaolong/Downloads/people.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, People.class)
                .sheet("模板")
                .doWrite(() -> {
                    // 分页查询数据
                    return data();
                });
    }
    @Test
    void ExcelReadTest(){
        String fileName = "/Users/fuxiaolong/Downloads/people.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, People.class, new DemoDataListenerP()).sheet().doRead();
    }

}
