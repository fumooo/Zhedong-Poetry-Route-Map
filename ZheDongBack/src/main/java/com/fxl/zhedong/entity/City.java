package com.fxl.zhedong.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class City {
    @TableId(value = "id",type = IdType.AUTO)
    @ExcelProperty("id")
    private Integer id;
    @ExcelProperty("城市名称")
    private String cname;
    @ExcelProperty("经度")
    private Double lng;
    @ExcelProperty("纬度")
    private Double lat;
    @ExcelProperty("省份")
    private String province;
    private Integer CityPoemNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getCityPoemNumber() {
        return CityPoemNumber;
    }

    public void setCityPoemNumber(Integer cityPoemNumber) {
        CityPoemNumber = cityPoemNumber;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cname='" + cname + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", province='" + province + '\'' +
                ", CityPoemNumber=" + CityPoemNumber +
                '}';
    }
}
