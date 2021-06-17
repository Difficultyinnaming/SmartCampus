package com.hss.campus.dao;

import com.hss.campus.entity.repair.RepairWorker;

import java.util.List;

public interface RepairWorkerDao {
    //添加维修工
    int insert(RepairWorker worker);
    //删除维修工
    int delete(Integer id);
    //更新维修工手机号，部门
    int update(RepairWorker worker);
    //查询所有维修工
    List<RepairWorker> queryAll();

    //根据部门查询维修工
    List<RepairWorker> queryTypeWorker(String unit);
    //根据状态查询维修工
    List<RepairWorker> queryOne(String status);
    //更新维修进度
    boolean updateWorkerStatus(String status, String name, String phone);

}
