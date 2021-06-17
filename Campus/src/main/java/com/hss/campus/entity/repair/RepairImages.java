package com.hss.campus.entity.repair;

public class RepairImages {
    private Integer id;
    private String mark;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RepairImages{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
