package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.repair.Unit;
import com.hss.campus.service.UnitService;
import com.hss.campus.util.Response;
import com.hss.campus.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UnitController {
    @Autowired
    private UnitService unitService;

    // 获取所有维修部门
    @RequestMapping(value = "/getAllUnit.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryUint(){
        List<Unit> allUnit = unitService.queryAllUnit();
        Response<List<Unit>> response=new Response<>();
        if (allUnit!=null){
            response.setData(allUnit);
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
        }else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //根据部门获取维修数量
    @RequestMapping(value = "/getUnitFinishNum.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String QueryUintFinishServlet(String unit){
        Response<Integer> response=new Response<>();
        response.setData(unitService.queryUnitFinishNum(unit));
        response.setCode(ResultCode.SUCCESS.code());
        response.setStatus(ResultCode.SUCCESS.status());
        return new Gson().toJson(response);
    }
}
