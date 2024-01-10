package com.fxl.zhedong.entity.PeopleAssQuery;
import com.fxl.zhedong.entity.Poem;

import java.util.List;

public class PeopleAssExperience {
    private Integer id;
    private Integer groupId;
    private Integer beginYear;
    private Integer endYear;
    private String document;
    private List<Poem> poem;

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

    public List<Poem> getPoem() {
        return poem;
    }

    public void setPoem(List<Poem> poem) {
        this.poem = poem;
    }

    @Override
    public String toString() {
        return "PeopleAssExperience{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", beginYear=" + beginYear +
                ", endYear=" + endYear +
                ", document='" + document + '\'' +
                ", poem=" + poem +
                '}';
    }
}
