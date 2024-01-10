package com.fxl.zhedong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fxl.zhedong.mapper")
public class ZheDongApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZheDongApplication.class, args);
    }

}
