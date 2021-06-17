package com.hss.campus.entity.dynamic;

public class Images {
    private Integer id;
    private String mark;
    private String url;

    public Images() {
    }

    public Images(Integer id, String mark, String url) {
        this.id = id;
        this.mark = mark;
        this.url = url;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
