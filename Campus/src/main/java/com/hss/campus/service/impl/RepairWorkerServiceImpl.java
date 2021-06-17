package com.hss.campus.service.impl;

import com.hss.campus.dao.RepairWorkerDao;
import com.hss.campus.entity.repair.RepairWorker;
import com.hss.campus.service.repair.RepairWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairWorkerServiceImpl implements RepairWorkerService {

    @Autowired
    private RepairWorkerDao repairWorkerDao;

    //添加维修工
    @Override
    public int insert(RepairWorker worker) {
        return repairWorkerDao.insert(worker);
    }
    //删除维修工
    @Override
    public int delete(Integer id) {
        return repairWorkerDao.delete(id);
    }
    //更新维修工手机号，部门
    @Override
    public int update(RepairWorker worker) {
        return repairWorkerDao.update(worker);
    }
    //查询所有维修工
    @Override
    public List<RepairWorker> queryAll() {
        return repairWorkerDao.queryAll();
    }

    //根据状态查询维修工
    @Override
    public List<RepairWorker> queryOne(String status) {
        return repairWorkerDao.queryOne(status);
    }
    //更新维修进度
    @Override
    public boolean updateWorkerStatus(String status, String name, String phone) {
        return repairWorkerDao.updateWorkerStatus(status,name,phone);
    }
    //根据部门查询维修工
    @Override
    public List<RepairWorker> queryTypeWorker(String unit) {
        return repairWorkerDao.queryTypeWorker(unit);
    }
}
