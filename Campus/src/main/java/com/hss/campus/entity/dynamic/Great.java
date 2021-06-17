package com.hss.campus.entity.dynamic;

public class Great {
    private Integer id;
    private Integer dynamicId;
    private Integer sId;
    private String time;
    private String name;
    private String avatar;

    public boolean isRealNull(){

        return dynamicId==0 || sId==0 || time.equals("");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "Great{" +
                "id=" + id +
                ", dynamicId=" + dynamicId +
                ", sId=" + sId +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
