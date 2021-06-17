package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.repair.Repair;
import com.hss.campus.entity.repair.RepairArea;
import com.hss.campus.entity.repair.RepairNum;
import com.hss.campus.entity.user.Administrator;
import com.hss.campus.entity.util.DayRepairNum;
import com.hss.campus.entity.util.ImageHeader;
import com.hss.campus.entity.util.ImageResponse;
import com.hss.campus.service.repair.RepairService;
import com.hss.campus.service.user.AdministratorService;
import com.hss.campus.util.FileUtil;
import com.hss.campus.util.OtherUtil;
import com.hss.campus.util.Response;
import com.hss.campus.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//管理员
@Controller
public class AdministratorController {

    @Autowired
    private AdministratorService service;
    @Autowired
    private RepairService repairService;


    //登录
    @RequestMapping(value = "/postAdministratorLogin.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String administratorLogin(@RequestBody Administrator administrator){
        Response<Administrator> resp=new Response<>();
        Integer id=administrator.getId();
        String password= administrator.getPassword();
        Administrator login=service.login(id,password);
        if (login!=null){
            login.setAvatar( OtherUtil.IP_ADDRESS+login.getAvatar());
            resp.setData(login);
            resp.setCode(ResultCode.SUCCESS.code());
            resp.setStatus(ResultCode.SUCCESS.status());
        }else {
            resp.setCode(ResultCode.USER_LOGIN_ERROR.code());
            resp.setStatus(ResultCode.USER_LOGIN_ERROR.status());
        }
        return new Gson().toJson(resp);
    }

    //上传头像
    @RequestMapping(value = "/updateAdminHeaderServer.do",method = RequestMethod.POST)
    @ResponseBody
    public String updateAdministratorOne(MultipartFile fileName) throws IOException {
        Response<ImageResponse> response=new Response<>();
        ImageResponse imageResponse = FileUtil.uploadAPicture("administrator\\",fileName);
        if (!"".equals(imageResponse.getFileName())){
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(new ImageResponse(OtherUtil.IP_ADDRESS+"image/administrator/"+imageResponse.getFileName()));
        }else {
            response.setCode(ResultCode.USER_LOGIN_ERROR.code());
            response.setStatus(ResultCode.USER_AVATAR_ERROR.status());
        }
        return new Gson().toJson(response);
    }
    @RequestMapping(value = "/uploadAdminHeaderDatabase.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String updateAdministratorTwo(@RequestBody ImageHeader imageHeader){
        Response response=new Response();
        if (imageHeader.isRealNull()){
            response.setCode(ResultCode.USER_LOGIN_ERROR.code());
            response.setStatus(ResultCode.USER_TEXT_ERROR.status());
        }else {
            String url = OtherUtil.cutImageUrl(imageHeader.getFileName());
            if (!"".equals(url)) {
                imageHeader.setFileName(url);
            }
            service.modifyAvatar(imageHeader.getFileName(), imageHeader.getId());
            if (service.modifyAvatar(imageHeader.getFileName(), imageHeader.getId()) != -1){
                response.setCode(ResultCode.SUCCESS.code());
                response.setStatus(ResultCode.SUCCESS.status());
            }else {
                response.setCode(ResultCode.USER_LOGIN_ERROR.code());
                response.setStatus(ResultCode.USER_TEXT_ERROR.status());
            }
        }
        return new Gson().toJson(response);
    }

    // 管理员获取报修数量（今日）
    @RequestMapping(value = "/getTodayNewRepair.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String todayNewRepairToDay(){
        Response<Long> response=new Response<>();
        response.setStatus(ResultCode.SUCCESS.status());
        response.setCode(ResultCode.SUCCESS.code());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(new Date()));

        response.setData(repairService.queryRepairNumToday(formatter.format(new Date())));
        return new Gson().toJson(response);
    }
    // 管理员获取报修数量（全部）
    @RequestMapping(value = "/allNewRepair.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String todayNewRepairAll(){
        Response<Long> response=new Response<>();
        response.setStatus(ResultCode.SUCCESS.status());
        response.setCode(ResultCode.SUCCESS.code());
        response.setData(repairService.queryRepairNumAll());
        return new Gson().toJson(response);
    }

    //管理员根据区域获取报修记录
    @RequestMapping(value = "/getRepairListByArea.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryRepairByArea(String area){
        Response<List<Repair>> response=new Response<>();
        List<Repair> list = repairService.queryRepairListByArea(area);
        if (list==null){
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
        }else {
            response.setStatus(ResultCode.SUCCESS.status());
            response.setCode(ResultCode.SUCCESS.code());
            response.setData(list);
        }
        response.setStatus(ResultCode.SUCCESS.status());
        response.setCode(ResultCode.SUCCESS.code());
        return new Gson().toJson(response);
    }

    //近七天的订单数量
    @RequestMapping(value = "/getWeekNum.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryWeekNum(){
        Response<List<DayRepairNum>> response=new Response<>();
        List<DayRepairNum> list=new ArrayList<>();
        for (int i = 6; i >-1 ; i--) {
            String day = OtherUtil.getOneDayByDistance(i);
            list.add(new DayRepairNum(day,repairService.queryBeforeWeek(day)));
        }
        response.setStatus(ResultCode.SUCCESS.status());
        response.setCode(ResultCode.SUCCESS.code());
        response.setData(list);
        return new Gson().toJson(response);
    }

    //今天新增的订单记录
    @RequestMapping(value = "/getRepairListByToday.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryRepairByToday(){
        Response<List<Repair>>response=new Response<>();
        String day = OtherUtil.getOneDayByDistance(0);
        List<Repair> list = repairService.getRepairListByToday(day);
        if (list == null){
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
        }else {
            response.setStatus(ResultCode.SUCCESS.status());
            response.setCode(ResultCode.SUCCESS.code());
            response.setData(list);
        }
        response.setStatus(ResultCode.SUCCESS.status());
        response.setCode(ResultCode.SUCCESS.code());
        return new Gson().toJson(response);
    }

    //获取全部区域的订单数量(有问题暂缓)
    @RequestMapping(value = "/getRepairNumListByArea.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryRepairNumByArea(){
        List<String> area=new ArrayList<>();
        Response<List<RepairNum>> response=new Response<>();
        List<RepairNum> repairNums = new ArrayList<>();
        List<RepairArea> query = repairService.queryArea();
        String schedule1,schedule2,schedule3,schedule4;
        for (RepairArea repairArea : query) {
            area.add(repairArea.getSmallArea());
        }
        List<String> collectArea = area.stream().distinct().collect(Collectors.toList());
        for (String str : collectArea) {
            schedule1=OtherUtil.REPAIR_STATUS[0];
            schedule2=OtherUtil.REPAIR_STATUS[1];
            schedule3=OtherUtil.REPAIR_STATUS[2];
            schedule4=OtherUtil.REPAIR_STATUS[5];
            repairNums.add(new RepairNum(repairService.queryRepairNum(str,schedule1),
                    repairService.queryRepairOtherNum(str,schedule2,schedule3,schedule4),str));
        }
        response.setData(repairNums);
        response.setStatus(ResultCode.SUCCESS.status());
        response.setCode(ResultCode.SUCCESS.code());
        return new Gson().toJson(response);
    }
}
