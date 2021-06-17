package com.hss.campus.entity.repair;

//维修记录
public class RepairRecord {
    private String  name,time,status,phone;
    private Integer repairId;

    public RepairRecord() {
    }

    public RepairRecord(String name, String time, String status, String phone, Integer repairId) {
        this.name = name;
        this.time = time;
        this.status = status;
        this.phone = phone;
        this.repairId = repairId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRepairId() {
        return repairId;
    }

    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
    }

    @Override
    public String toString() {
        return "RepairRecord{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                ", repairId=" + repairId +
                '}';
    }
}
