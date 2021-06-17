package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.repair.RepairRecord;
import com.hss.campus.service.repair.RepairRecordService;
import com.hss.campus.service.repair.RepairService;
import com.hss.campus.util.OtherUtil;
import com.hss.campus.util.Response;
import com.hss.campus.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//维修记录
@Controller
public class RepairRecordController {
    @Autowired
    private RepairRecordService repairRecordService;
    @Autowired
    private RepairService repairService;


    //查看维修记录
    @RequestMapping(value = "/getRecordByRepairId.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String showRepairRecord(Integer repairId){
        Response<List<RepairRecord>> response=new Response<>();
        List<RepairRecord> list = repairRecordService.querySimpleRecord(repairId);
        if (list !=null){
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(list);
        }else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //管理员受理维修订单
    @RequestMapping(value = "/updateAccept.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String updateAccept(String name,Integer id){
        Response response=new Response();
        String time= OtherUtil.getNowTime("yyyy-MM-dd HH:mm:ss");
        //插入受理记录
        boolean insert  = repairRecordService.addRecord(name,time,OtherUtil.REPAIR_SCHEDULE[0],id,"");
        //维修订单已受理
        boolean update = repairService.modifyStatus(OtherUtil.REPAIR_STATUS[1],id);
        if (insert&&update){
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
        }else {
            response.setCode(ResultCode.USER_LOGIN_ERROR.code());
            response.setStatus(ResultCode.USER_LOGIN_ERROR.status());
        }
        return new Gson().toJson(response);
    }

}
