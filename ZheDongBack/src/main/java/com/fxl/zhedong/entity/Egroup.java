package com.fxl.zhedong.entity;

public class Egroup {
    private Integer id;
    private Integer beginYear;
    private Integer endYear;
    private Integer peopleId;
    private Integer cityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(Integer beginYear) {
        this.beginYear = beginYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", beginYear=" + beginYear +
                ", endYear=" + endYear +
                ", peopleId=" + peopleId +
                ", cityId=" + cityId +
                '}';
    }
}
