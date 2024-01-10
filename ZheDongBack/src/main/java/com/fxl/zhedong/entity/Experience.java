package com.fxl.zhedong.entity;

public class Experience {
    private Integer id;
    private Integer groupId;
    private Integer beginYear;
    private Integer endYear;
    private String document;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", beginYear=" + beginYear +
                ", endYear=" + endYear +
                ", document='" + document + '\'' +
                '}';
    }
}
