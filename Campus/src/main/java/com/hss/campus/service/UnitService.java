package com.hss.campus.service;

import com.hss.campus.entity.repair.Unit;

import java.util.List;

public interface UnitService {
    int add(Unit unit);
    //查看维修部门
    List<Unit> queryAllUnit();
    //完成订单数
    Integer queryUnitFinishNum(String unit);
    boolean UpdateFinished(String unit);
    Integer queryIdByName(String name);
}
