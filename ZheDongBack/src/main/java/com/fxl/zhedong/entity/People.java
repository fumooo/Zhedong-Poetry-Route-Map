package com.fxl.zhedong.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class People {
    @TableId(value = "id",type= IdType.AUTO)
    @ExcelProperty("id")
    private Integer id;
    @ExcelProperty("诗人姓名")
    private String name;
    @ExcelProperty("出生日期")
    private String birthDate;
    @ExcelProperty("死亡日期")
    private String deathDate;
    @ExcelProperty("诗人简介")
    private String introduction;
    @ExcelIgnore
    private String pictureP;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPictureP() {
        return pictureP;
    }

    public void setPictureP(String pictureP) {
        this.pictureP = pictureP;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", deathDate='" + deathDate + '\'' +
                ", introduction='" + introduction + '\'' +
                ", pictureP='" + pictureP + '\'' +
                '}';
    }
}
