package com.hss.campus.entity.dynamic;

import java.util.List;

public class Dynamic {
    private Integer id;
    private Integer sId;
    private String content;
    private String mark;
    private Integer status;
    private String time;
    private List<Images> imagesList;
    private String name;
    private String avatar;
    private Integer greatNum;

    public Integer getGreatNum() {
        return greatNum;
    }

    public void setGreatNum(Integer greatNum) {
        this.greatNum = greatNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Images> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    @Override
    public String toString() {
        return "Dynamic{" +
                "id=" + id +
                ", sId=" + sId +
                ", content='" + content + '\'' +
                ", mark='" + mark + '\'' +
                ", status=" + status +
                ", time='" + time + '\'' +
                ", imagesList=" + imagesList +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", greatNum=" + greatNum +
                '}';
    }
}
