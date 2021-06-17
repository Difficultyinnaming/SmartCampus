package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.repair.Device;
import com.hss.campus.service.DeviceService;
import com.hss.campus.util.Response;
import com.hss.campus.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//设备
@Controller
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    //添加设备
    @RequestMapping(value = "/addDevice.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String insertDevice(@RequestBody Device device){
        Response<Integer>response=new Response<>();
        //if (!device.isNull()){
            if (deviceService.add(device) != -1){
                response.setStatus(ResultCode.SUCCESS.status());
                response.setCode(ResultCode.SUCCESS.code());
            }else {
                response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
                response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            }
//        }else {
//            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
//            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
//        }
        return new Gson().toJson(response);
    }

    //删除设备
    @RequestMapping(value = "/removeDevice.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String deleteDevice(Integer id){
        Response<Object> response = new Response<>();
        if (deviceService.deleteById(id) != -1){
            response.setStatus(ResultCode.SUCCESS.status());
            response.setCode(ResultCode.SUCCESS.code());
        }else {
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
        }
        System.out.println(response);
        return new Gson().toJson(response);
    }

    //查找所有设备
    @RequestMapping(value = "/queryAllDevice.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String QueryAllDeviceServlet(){
        List<Device> list = deviceService.queryAll();
        Response<List<Device>> response = new Response<>();
        if (list!=null){
            response.setStatus(ResultCode.SUCCESS.status());
            response.setCode(ResultCode.SUCCESS.code());
            response.setData(list);
        }else {
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
        }
        return new Gson().toJson(response);
    }

    //更新设备
    @RequestMapping(value = "/updateDevice.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String updateDevice(@RequestBody Device entity){
        Response<Integer>response=new Response<>();
        //if (!entity.isNull()){
            if (deviceService.modify(entity) != -1){
                response.setStatus(ResultCode.SUCCESS.status());
                response.setCode(ResultCode.SUCCESS.code());
            }else {
                response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
                response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            }
//        }else {
//            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
//            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
//        }
        return new Gson().toJson(response);
    }
}
