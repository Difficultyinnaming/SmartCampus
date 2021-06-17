package com.hss.campus.service.impl;

import com.hss.campus.dao.RepairDao;
import com.hss.campus.entity.repair.Repair;
import com.hss.campus.entity.repair.RepairArea;
import com.hss.campus.entity.repair.RepairProject;
import com.hss.campus.entity.repair.RepairWorker;
import com.hss.campus.service.repair.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairDao repairDao;

    //查找区域
    @Override
    public List<RepairArea> queryArea() {
        return repairDao.queryArea();
    }
    //查找项目
    @Override
    public List<RepairProject> queryProject() {
        return repairDao.queryProject();
    }
    //报修上传
    @Override
    public int add(Repair repair) {
        return repairDao.add(repair);
    }
    //显示报修
    @Override
    public Repair query(Integer id) {
        return repairDao.query(id);
    }
    //报修列表
    @Override
    public List<Repair> queryList() {
        return repairDao.queryList();
    }
    //学生查看报修
    @Override
    public List<Repair> queryByStuId(Integer id) {
        return repairDao.queryByStuId(id);
    }
    //学生查看报修进度
    @Override
    public List<Repair> queryByStuSchedule(Integer id) {
        return repairDao.queryByStuSchedule(id);
    }
    //管理员查看报修进度
    @Override
    public List<Repair> queryByAdminSchedule(String schedule) {
        return repairDao.queryByAdminSchedule(schedule);
    }
    //更新维修进度
    @Override
    public boolean modifyStatus(String status,Integer id) {
        return repairDao.updateStatus(status,id);
    }
    //更新维修进度，评价，描述
    @Override
    public boolean modifyAppraiseRepair(String schedule,String appraise, String description,Integer repairId) {
        return repairDao.appraiseRepair(schedule,appraise,description,repairId);
    }
    //派遣维修工
    @Override
    public boolean addSendWorker(String name, Integer repairId) {
        return repairDao.sendWorker(name,repairId);
    }
    //今日报修数量
    @Override
    public Long queryRepairNumToday(String day) {
        return repairDao.queryRepairNumToday(day);
    }
    //所有报修数量
    @Override
    public Long queryRepairNumAll() {
        return repairDao.queryRepairNumAll();
    }
    //根据区域获取报修记录
    @Override
    public List<Repair> queryRepairListByArea(String area) {
        return repairDao.queryRepairListByArea(area);
    }
    //一周报修数量
    @Override
    public Long queryBeforeWeek(String day) {
        return repairDao.queryBeforeWeek(day);
    }
    //今日新增订单
    @Override
    public List<Repair> getRepairListByToday(String day) {
        return repairDao.getRepairListByToday(day);
    }
    //扫码报修
    @Override
    public boolean addByCode(Repair repair) {
        return repairDao.addByCode(repair);
    }

    //获取全部区域的订单数量
    @Override
    public Long queryRepairNum(String area,String schedule) {
        return repairDao.getRepairNum(area,schedule);
    }
    @Override
    public Long queryRepairOtherNum(String area,String schedule1,String schedule2,String schedule3) {
        return repairDao.getRepairOtherNum(area,schedule1,schedule2,schedule3);
    }

    //根据姓名查询维修工信息
    @Override
    public String queryId(Integer id) {
        return repairDao.queryId(id);
    }
    @Override
    public RepairWorker queryWorker(String name) {
        return repairDao.queryWorker(name);
    }

    //查询部门名
    @Override
    public int queryUnit(String name) {
        return repairDao.queryUnit(name);
    }
    @Override
    public String queryUnitNameByRepairId(Integer id) {
        return repairDao.getUnitNameByRepairId(id);
    }

}
