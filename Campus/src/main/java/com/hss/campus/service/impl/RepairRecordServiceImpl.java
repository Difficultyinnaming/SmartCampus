package com.hss.campus.service.impl;

import com.hss.campus.dao.RepairRecordDao;
import com.hss.campus.entity.repair.RepairRecord;
import com.hss.campus.service.repair.RepairRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairRecordServiceImpl implements RepairRecordService {
    @Autowired
    private RepairRecordDao repairRecordDao;

    //插入维修记录
    @Override
    public boolean addRecord(String name,String time,String status,Integer repairId,String phone) {
        return repairRecordDao.insertRecord(name,time,status,repairId,phone);
    }
    //查看维修记录
    @Override
    public List<RepairRecord> querySimpleRecord(Integer repairId) {
        return repairRecordDao.querySimpleRecord(repairId);
    }
}
