package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.repair.RepairWorker;
import com.hss.campus.service.UnitService;
import com.hss.campus.service.repair.RepairRecordService;
import com.hss.campus.service.repair.RepairService;
import com.hss.campus.service.repair.RepairWorkerService;
import com.hss.campus.util.OtherUtil;
import com.hss.campus.util.Response;
import com.hss.campus.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//维修工人
@Controller
public class RepairWorkerController {

    @Autowired
    private RepairWorkerService workerService;
    @Autowired
    private RepairService repairService;
    @Autowired
    private RepairRecordService recordService;
    @Autowired
    private UnitService unitService;

    //获取全部维修人员列表
    @RequestMapping(value = "/queryAllRepairWorker.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryRepairWorker(){
        Response<List<RepairWorker>> response=new Response<>();
        List<RepairWorker> list = workerService.queryAll();
        if (list!=null){
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(list);
        }else {
            response.setCode(ResultCode.USER_LOGIN_ERROR.code());
            response.setStatus(ResultCode.USER_AVATAR_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //增加维修人员
    @RequestMapping(value = "/insertWorker.do",method = RequestMethod.POST)
    @ResponseBody
    public String insertWorker(@RequestBody RepairWorker entity){
        Response<Integer> response=new Response<>();
        if (entity!=null){
            entity.setStatus(OtherUtil.REPAIR_WORKER_STATUS[1]);
            if (workerService.insert(entity)!=-1){
                response.setCode(ResultCode.SUCCESS.code());
                response.setStatus(ResultCode.SUCCESS.status());
            }else {
                response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
                response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
            }
        }else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //更改维修人员信息
    @RequestMapping(value = "/updateWorker.do",method = RequestMethod.POST)
    @ResponseBody
    public String updateWorker(@RequestBody RepairWorker entity){
        Response<Integer> response=new Response<>();
        if (entity!=null){
            if (workerService.update(entity)!=-1){
                response.setCode(ResultCode.SUCCESS.code());
                response.setStatus(ResultCode.SUCCESS.status());
            }else {
                response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
                response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
            }
        }else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //删除维修工人
    @RequestMapping(value = "/deleteWorker.do",method = RequestMethod.POST)
    @ResponseBody
    public String deleteWorker(Integer id){
        Response<Integer> response = new Response<>();
        if (workerService.delete(id) != -1) {
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
        } else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //根据部门查询维修人员
    @RequestMapping(value = "/queryTypeWorker.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String QueryTypeWorkerServlet(String unit){
        Response<List<RepairWorker>> response = new Response<>();
        List<RepairWorker> workerList = workerService.queryTypeWorker(unit);
        if (workerList != null) {
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(workerList);
        } else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //派遣维修工
    @RequestMapping(value = "/updateDispatch.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String updateDispatch(String name,Integer repairId,String phone){
        Response response=new Response();
        String time= OtherUtil.getNowTime("yyyy-MM-dd HH:mm:ss");
        //判断维修工是否空闲
        if (!"等待中".equals(workerService.queryOne(OtherUtil.REPAIR_WORKER_STATUS[1]))){
            //插入受理记录
            boolean insert  = recordService.addRecord(name,time,OtherUtil.REPAIR_SCHEDULE[1], repairId,phone);
            //维修订单已受理
            boolean update = repairService.modifyStatus(OtherUtil.REPAIR_STATUS[2],repairId);
            //维修订单插入维修工
            boolean insertWorker = repairService.addSendWorker(name,repairId);
            //更新维修工的状态
            boolean workerStatus = workerService.updateWorkerStatus(OtherUtil.REPAIR_WORKER_STATUS[0],name, phone);
            if (insert&&update&workerStatus&insertWorker){
                response.setCode(ResultCode.SUCCESS.code());
                response.setStatus(ResultCode.SUCCESS.status());
            }else {
                response.setCode(ResultCode.USER_LOGIN_ERROR.code());
                response.setStatus(ResultCode.USER_LOGIN_ERROR.status());
            }
        }else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //维修工签到
    @RequestMapping(value = "/workSign.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String updateSign(Integer repairId){
        Response response = new Response();
        String time= OtherUtil.getNowTime("yyyy-MM-dd HH:mm:ss");
        //判断维修工是否维修中
        RepairWorker repairWorker = repairService.queryWorker(repairService.queryId(repairId));
       // if (!repairWorker.isNull()) {
        System.out.println(repairWorker);
            //插入维修工签到记录
            boolean updateStatus = repairService.modifyStatus(OtherUtil.REPAIR_STATUS[5],repairId);
            boolean insert = recordService.addRecord(repairWorker.getName(),time,OtherUtil.REPAIR_SCHEDULE[2], repairId, repairWorker.getPhone());
            if (insert&updateStatus) {
                response.setCode(ResultCode.SUCCESS.code());
                response.setStatus(ResultCode.SUCCESS.status());
            } else {
                response.setCode(ResultCode.USER_LOGIN_ERROR.code());
                response.setStatus(ResultCode.USER_LOGIN_ERROR.status());
            }
//        } else {
//            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
//            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
//        }
        return new Gson().toJson(response);
    }

    //维修完工
    @RequestMapping(value = "/updateFinished.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String updateFinished(Integer repairId){
        Response response=new Response();
        String time= OtherUtil.getNowTime("yyyy-MM-dd HH:mm:ss");
        RepairWorker repairWorker = repairService.queryWorker(repairService.queryId(repairId));
        //判断维修工是否存在且空闲
       // if (!repairWorker.isNull()){
            String name = repairWorker.getName();
            String phone = repairWorker.getPhone();
            //根据订单id为维修单位的维修数量+1
            String unitName = repairService.queryUnitNameByRepairId( repairService.queryUnit(repairService.queryId(repairId)));
            boolean finished = unitService.UpdateFinished(unitName);
            //插入受理记录
            boolean insert  = recordService.addRecord(name,time,OtherUtil.REPAIR_SCHEDULE[3], repairId,phone);
            //维修订单已受理
            boolean update = repairService.modifyStatus(OtherUtil.REPAIR_STATUS[3],repairId);
            //更新维修工的状态
            boolean workerStatus = workerService.updateWorkerStatus(OtherUtil.REPAIR_WORKER_STATUS[1], name, phone);
            if (insert&&update&workerStatus){
                response.setCode(ResultCode.SUCCESS.code());
                response.setStatus(ResultCode.SUCCESS.status());
            }else {
                response.setCode(ResultCode.USER_LOGIN_ERROR.code());
                response.setStatus(ResultCode.USER_LOGIN_ERROR.status());
            }
//        }else {
//            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
//            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
//        }
        return new Gson().toJson(response);
    }
}
