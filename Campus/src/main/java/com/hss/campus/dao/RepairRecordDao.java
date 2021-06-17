package com.hss.campus.dao;

import com.hss.campus.entity.repair.RepairRecord;

import java.util.List;

public interface RepairRecordDao {
    //插入维修记录
    boolean insertRecord(String name, String time, String status, Integer repairId, String phone);
    //查看维修记录
    List<RepairRecord> querySimpleRecord(Integer repairId);
}
