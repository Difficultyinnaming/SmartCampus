package com.hss.campus.entity.repair;

//维修工人
public class RepairWorker {
    private Integer id;
    private String name,phone,email,unit,status;
    public boolean isNull(){
        return "".equals(status);
    }

    public RepairWorker() {
    }

    public RepairWorker(Integer id, String name, String phone, String email, String unit, String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.unit = unit;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RepairWorker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", unit='" + unit + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
