package com.hss.campus.service.impl;

import com.hss.campus.dao.DeviceDao;
import com.hss.campus.entity.repair.Device;
import com.hss.campus.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDao deviceDao;

    //插入设备
    @Override
    public int add(Device device) {
        return deviceDao.add(device);
    }
    //删除设备
    @Override
    public int deleteById(Integer id) {
        return deviceDao.deleteById(id);
    }
    //更新设备
    @Override
    public int modify(Device device) {
        return deviceDao.modify(device);
    }
    //查找设备
    @Override
    public List<Device> queryAll() {
        return deviceDao.queryAll();
    }
}
