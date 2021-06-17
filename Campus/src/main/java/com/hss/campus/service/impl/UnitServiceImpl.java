package com.hss.campus.service.impl;

import com.hss.campus.dao.UnitDao;
import com.hss.campus.entity.repair.Unit;
import com.hss.campus.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitDao unitDao;

    @Override
    public int add(Unit unit) {
        return 0;
    }
    //查看维修部门
    @Override
    public List<Unit> queryAllUnit() {
        return unitDao.queryAllUnit();
    }
    //完成订单数
    @Override
    public Integer queryUnitFinishNum(String unit) {
        return unitDao.queryUnitFinishNum(unit);
    }
    @Override
    public boolean UpdateFinished(String unit) {
        return unitDao.UpdateFinished(unit);
    }
    @Override
    public Integer queryIdByName(String name) {
        return null;
    }
}
