package com.hss.campus.dao;

import com.hss.campus.entity.repair.Repair;
import com.hss.campus.entity.repair.RepairArea;
import com.hss.campus.entity.repair.RepairProject;
import com.hss.campus.entity.repair.RepairWorker;

import java.util.List;

public interface RepairDao {
    //查找区域
    List<RepairArea> queryArea();
    //查找项目
    List<RepairProject> queryProject();
    //报修上传
    int add(Repair repair);
    //显示报修
    Repair query(Integer id);
    //报修列表
    List<Repair> queryList();
    //学生查看报修
    List<Repair> queryByStuId(Integer id);
    //学生查看报修进度
    List<Repair> queryByStuSchedule(Integer id);
    //管理员查看报修进度
    List<Repair> queryByAdminSchedule(String schedule);
    //更新维修进度
    boolean updateStatus(String status, Integer id);
    //更新维修进度，评价，描述
    boolean appraiseRepair(String schedule, String appraise, String description, Integer repairId);
    //派遣维修工
    boolean sendWorker(String name, Integer repairId);
    //今日报修数量
    Long queryRepairNumToday(String day);
    //所有报修数量
    Long queryRepairNumAll();
    //根据区域获取报修记录
    List<Repair> queryRepairListByArea(String area);
    //一周报修数量
    Long queryBeforeWeek(String day);
    //今日新增订单
    List<Repair> getRepairListByToday(String day);

    //扫码报修
    boolean addByCode(Repair repair);
    //获取全部区域的订单数量
    Long getRepairNum(String area, String schedule);
    Long getRepairOtherNum(String area, String schedule1, String schedule2, String schedule3);

    //根据姓名查询维修工信息
    String queryId(Integer id);
    RepairWorker queryWorker(String name);

    //查询部门名
    int queryUnit(String name);
    String getUnitNameByRepairId(Integer id);
}
