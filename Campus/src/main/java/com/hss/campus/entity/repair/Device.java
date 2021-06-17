package com.hss.campus.entity.repair;

public class Device {
    private Integer id;
    private String area;
    private String project;
    private String address;
    private String worker;
    private String codeUrl;

    public Device() {
    }

    public Device(Integer id, String area, String project, String address, String worker, String codeUrl) {
        this.id = id;
        this.area = area;
        this.project = project;
        this.address = address;
        this.worker = worker;
        this.codeUrl = codeUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", project='" + project + '\'' +
                ", address='" + address + '\'' +
                ", worker='" + worker + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                '}';
    }
}
