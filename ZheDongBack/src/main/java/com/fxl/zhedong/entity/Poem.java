package com.fxl.zhedong.entity;

public class Poem {
    private Integer id;
    private Integer experienceId;
    private String poemTitle;
    private String content;
    private String dynasty;
    private Integer year;
    private String source;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Integer experienceId) {
        this.experienceId = experienceId;
    }

    public String getPoemTitle() {
        return poemTitle;
    }

    public void setPoemTitle(String poemTitle) {
        this.poemTitle = poemTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "poem{" +
                "id=" + id +
                ", experienceId=" + experienceId +
                ", poemTitle='" + poemTitle + '\'' +
                ", content='" + content + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", year=" + year +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
