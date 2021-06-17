package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.repair.Repair;
import com.hss.campus.entity.user.Student;
import com.hss.campus.entity.util.ImageHeader;
import com.hss.campus.entity.util.ImageResponse;
import com.hss.campus.service.repair.RepairImagesService;
import com.hss.campus.service.repair.RepairRecordService;
import com.hss.campus.service.repair.RepairService;
import com.hss.campus.service.user.StudentService;
import com.hss.campus.util.FileUtil;
import com.hss.campus.util.OtherUtil;
import com.hss.campus.util.Response;
import com.hss.campus.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

//学生
@Controller
public class StudentController {

    @Autowired
    private StudentService service;
    @Autowired
    private RepairRecordService recordService;
    @Autowired
    private RepairService repairService;
    @Autowired
    private RepairImagesService repairImagesService;

    //注册
    @RequestMapping("/addStudent.do")
    public ModelAndView addStudent(Student student){
        ModelAndView mv=new ModelAndView();
        String tips="注册失败";
        //调用Service处理student
        System.out.println(student);
        int nums=service.addStudent(student);
        if (nums > 0){
            //注册成功
            tips="学生【"+student.getName()+"】注册成功";
        }
        //添加数据
        mv.addObject("tips",tips);
        //指定结果页面
        mv.setViewName("result");
        return mv;
    }

    //登录
    @RequestMapping(value = "/postStudentLogin.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String studentLogin(@RequestBody Student student){
        Response<Student> response=new Response<>();
        Integer id=student.getId();
        String password= student.getPassword();
        Student login=service.login(id,password);
        if (login != null){
            login.setAvatar( OtherUtil.IP_ADDRESS+login.getAvatar());
            response.setData(login);
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
        }else {
            response.setCode(ResultCode.USER_LOGIN_ERROR.code());
            response.setStatus(ResultCode.USER_LOGIN_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //上传头像
    @RequestMapping(value = "/updateStuHeaderServer.do",method = RequestMethod.POST)
    @ResponseBody
    public String updateStudentOne(MultipartFile fileName) throws IOException {
        Response<ImageResponse> response=new Response<>();
        ImageResponse imageResponse =  FileUtil.uploadAPicture("student\\",fileName);
        if (!"".equals(imageResponse.getFileName())){
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(new ImageResponse(OtherUtil.IP_ADDRESS+"image/student/"+imageResponse.getFileName()));
        }else {
            response.setCode(ResultCode.USER_LOGIN_ERROR.code());
            response.setStatus(ResultCode.USER_AVATAR_ERROR.status());
        }
        return new Gson().toJson(response);
    }
    @RequestMapping(value = "/uploadStuHeaderDatabase.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String updateStudentTwo(@RequestBody ImageHeader imageHeader){
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

    //学生评价
    @RequestMapping(value = "/updateAppraise.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String updateAppraise(String name,Integer repairId,String appraise,String description){
        Response response=new Response();
        String time= OtherUtil.getNowTime("yyyy-MM-dd HH:mm:ss");
        //插入受理记录
        boolean insert  = recordService.addRecord(name,time,OtherUtil.REPAIR_SCHEDULE[4], repairId,appraise);
        //维修订单已评价
        boolean update = repairService.modifyAppraiseRepair( OtherUtil.REPAIR_STATUS[4],appraise,description,repairId);
        if (insert&&update){
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
        }else {
            response.setCode(ResultCode.USER_LOGIN_ERROR.code());
            response.setStatus(ResultCode.USER_LOGIN_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //扫码报修
    @RequestMapping(value = "/byCode.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String RepairUploadByCode(@RequestBody Repair repair){
        Response<String> response = new Response<>();
        repair.setSchedule(OtherUtil.REPAIR_STATUS[0]);
        if (repairService.addByCode(repair)) {
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
        } else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_AVATAR_ERROR.status());
        }
        return new Gson().toJson(response);
    }
}
