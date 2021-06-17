package com.hss.campus.entity.dynamic;

public class Reply {
    private Integer id;
    private Integer sId;
    private String content;
    private String praseCount;
    private String time;

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

    public String getPraseCount() {
        return praseCount;
    }

    public void setPraseCount(String praseCount) {
        this.praseCount = praseCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", sId=" + sId +
                ", content='" + content + '\'' +
                ", praseCount='" + praseCount + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
