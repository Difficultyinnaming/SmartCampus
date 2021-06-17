package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.dynamic.Images;
import com.hss.campus.entity.repair.Repair;
import com.hss.campus.entity.repair.RepairArea;
import com.hss.campus.entity.repair.RepairProject;
import com.hss.campus.entity.util.ImageHeader;
import com.hss.campus.service.repair.RepairImagesService;
import com.hss.campus.service.repair.RepairService;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

//报修
@Controller
public class RepairController {

    @Autowired
    private RepairService repairService;
    @Autowired
    private RepairImagesService repairImagesService;

    //报修区域
    @RequestMapping(value = "/repairArea.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String repairArea(){
        Response<List<RepairArea>> response=new Response<>();
        List<RepairArea> list=repairService.queryArea();
        response.setCode(ResultCode.SUCCESS.code());
        response.setStatus(ResultCode.SUCCESS.status());
        response.setData(list);
        return new Gson().toJson(response);
    }
    //报修项目
    @RequestMapping(value = "/repairProject.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String repairProject(){
        Response<List<RepairProject>> response=new Response<>();
        List<RepairProject> listProject=repairService.queryProject();
        response.setCode(ResultCode.SUCCESS.code());
        response.setStatus(ResultCode.SUCCESS.status());
        response.setData(listProject);
        return new Gson().toJson(response);
    }
    //报修照片
    @RequestMapping(value = "/repairPhotoServer.do",method = RequestMethod.POST)
    @ResponseBody
    public String repairPhoto(MultipartFile[] fileName) throws IOException, InterruptedException {
        Response<ImageHeader> response=new Response<>();
        ImageHeader imageHeader=new ImageHeader();
        List<String> list = FileUtil.uploadMultiple("repair\\",fileName);
        String strUUID= UUID.randomUUID().toString().replace("-", "").toUpperCase();
        for (String s : list) {
            repairImagesService.add(OtherUtil.cutImageUrl(s),strUUID);
        }
        imageHeader.setFileName(strUUID);
        response.setCode(ResultCode.SUCCESS.code());
        response.setStatus(ResultCode.SUCCESS.status());
        response.setData(imageHeader);
        return new Gson().toJson(response);
    }
    //报修上传
    @RequestMapping(value = "/repairUploadDatabase.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String repairUpload(@RequestBody Repair repair){
        Response<String> response = new Response<>();
       // String url = OtherUtil.cutImageUrl(repair.getImage());
//        if (!"".equals(url)) {
//            repair.setImage(url);
//        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        repair.setDate(formatter.format(new Date()));
        repair.setSchedule(OtherUtil.REPAIR_STATUS[0]);
        if (repairService.add(repair) != -1) {
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
        } else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_AVATAR_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //报修列表
    @RequestMapping(value = "/repairList.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String repairList(){
        Response<List<Repair>> response=new Response<>();
        List<Repair> list=repairService.queryList();
        if (list!=null){
            for (Repair repair : list) {
                List<Images> imagesList = repairImagesService.queryUrl(repair.getMark());
                for (Images images : imagesList) {
                    images.setUrl(OtherUtil.IP_ADDRESS+images.getUrl());
                }
                repair.setImagesList(imagesList);
            }
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(list);
        }else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
        }
        return new Gson().toJson(response);
    }
    //报修列表详情
    @RequestMapping(value = "/showRepair.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String showRepair(Integer id){
        Response<Repair> response=new Response<>();
        Repair show=repairService.query(id);
        if (show !=null){
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(show);
        }
        return new Gson().toJson(response);
    }

    //学生查看报修
    @RequestMapping(value = "/viewRepairStu.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String ViewRepairServlet(Integer id){
        Response<List<Repair>> response=new Response<>();
        List<Repair> list=repairService.queryByStuId(id);
        if (list != null){
            for (Repair repair : list) {
                List<Images> imagesList = repairImagesService.queryUrl(repair.getMark());
//                if (!"".equals(repair.getImage())){
//                   // repair.setImage(OtherUtil.IP_ADDRESS + repair.getImage());
//                }
                for (Images images : imagesList) {
                    images.setUrl(OtherUtil.IP_ADDRESS+images.getUrl());
                }
                repair.setImagesList(imagesList);
            }
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(list);
        }else {
            response.setCode(ResultCode.USER_LOGIN_ERROR.code());
            response.setStatus(ResultCode.USER_TEXT_ERROR.status());
        }
        return new Gson().toJson(response);
    }
    //学生查看报修进度
    @RequestMapping(value = "/queryStudentScheduleList.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryStudentSchedule(Integer id){
        Response<List<Repair>> response=new Response();
        List<Repair> list = repairService.queryByStuSchedule(id);
        if (list!=null){
            for (Repair repair : list) {
                List<Images> imagesList = repairImagesService.queryUrl(repair.getMark());
                for (Images images : imagesList) {
                    images.setUrl(OtherUtil.IP_ADDRESS+images.getUrl());
                }
                repair.setImagesList(imagesList);
            }
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(list);
        }else {
            response.setCode(ResultCode.USER_LOGIN_ERROR.code());
            response.setStatus(ResultCode.USER_LOGIN_ERROR.status());
        }
        return new Gson().toJson(response);
    }
    //管理员查看报修进度
    @RequestMapping(value = "/queryAdministratorScheduleList.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryAdministratorSchedule(String schedule){
        Response<List<Repair>> response=new Response();
        List<Repair> list = repairService.queryByAdminSchedule(schedule);
        if (list!=null){
            for (Repair repair : list) {
                List<Images> imagesList = repairImagesService.queryUrl(repair.getMark());
                for (Images images : imagesList) {
                    images.setUrl(OtherUtil.IP_ADDRESS+images.getUrl());
                }
                repair.setImagesList(imagesList);
            }
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(list);
        }else {
            response.setCode(ResultCode.USER_LOGIN_ERROR.code());
            response.setStatus(ResultCode.USER_LOGIN_ERROR.status());
        }
        return new Gson().toJson(response);
    }
}
