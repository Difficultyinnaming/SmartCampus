package com.hss.campus.service;

import com.hss.campus.entity.repair.Device;

import java.util.List;

public interface DeviceService {
    //插入设备
    int add(Device device);
    //删除设备
    int deleteById(Integer id);
    //更新设备
    int modify(Device device);
    //查找设备
    List<Device> queryAll();
}
