package com.fxl.zhedong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class Scenery {
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;
    private Integer peopleId;
    private String name;
    private String introduction;
    private String picture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Scenery{" +
                "id=" + id +
                ", peopleId=" + peopleId +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
