package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.dynamic.Dynamic;
import com.hss.campus.entity.dynamic.Great;
import com.hss.campus.entity.user.Student;
import com.hss.campus.service.dynamic.DynamicService;
import com.hss.campus.service.dynamic.GreatService;
import com.hss.campus.service.user.StudentService;
import com.hss.campus.util.OtherUtil;
import com.hss.campus.util.Response;
import com.hss.campus.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreatController {

    @Autowired
    private GreatService greatService;
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private StudentService studentService;


    //查看点赞
    @RequestMapping(value = "/changeGreatStatus.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryGreat(Integer dynamicId, Integer sId) {
        Response<Integer> response = new Response<>();
        Great great = greatService.queryGreat(dynamicId, sId);
        Long greatNUm = dynamicService.queryGreatNUm(dynamicId);
        if (great==null){
            //无记录  点赞
            int add = greatService.addRecording(new Great(dynamicId,sId, OtherUtil.getNowTime("yyyy-MM-dd HH:mm:ss")));
            boolean plusNum;
            if (greatNUm==null){
                plusNum=(dynamicService.modifyNum(1, dynamicId)!=-1);
            }else {
                plusNum=(dynamicService.modifyNum((int) (greatNUm+1), dynamicId)!=-1);
            }
            if (add!=-1&&plusNum){ response.setStatus(ResultCode.SUCCESS.status());
                response.setCode(ResultCode.SUCCESS.code());
            }else {
                response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
                response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            }
        }else {
            //有记录  取消点赞
            boolean delete = greatService.deleteRecording(great.getId());
            int num = dynamicService.modifyNum((int) (greatNUm - 1), dynamicId);
            System.out.println(num);
            if (num!=-1&&delete){
                response.setStatus(ResultCode.SUCCESS.status());
                response.setCode(ResultCode.SUCCESS.code());
            }else {
                response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
                response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            }
        }
        return new Gson().toJson(response);
    }

    @RequestMapping(value = "/queryRecording.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryRecording(Integer sId){
        Response<List<Great>> response=new Response<>();
        if (sId != null){
            List<Great> greatList = new ArrayList<>();
            List<Dynamic> dynamicList = dynamicService.queryAll(sId, OtherUtil.UPLOAD_MULTIPLE_PICTURES[0]);
            for (Dynamic dynamic : dynamicList) {
                Integer dynamicId = dynamic.getId();
                System.out.println("-----------------"+dynamicId);
                List<Great> list = greatService.queryGreatRecord(dynamicId);
                for (int i = 0; i < list.size(); i++) {
                    Great great = list.get(i);
                    Student student = studentService.queryNameAvatar(great.getsId());
                    great.setName(student.getName());
                    great.setAvatar(OtherUtil.IP_ADDRESS+student.getAvatar());
                    list.set(i, great);
                }
                greatList.addAll(list);
            }
            //先根据sId获取动态列表，再根据动态列表的id获取点赞列表，在根据其分别的sId获取姓名，头像
            response.setData(greatList);
            response.setStatus(ResultCode.SUCCESS.status());
            response.setCode(ResultCode.SUCCESS.code());
        }else {
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
        }
        return new Gson().toJson(response);
    }

    @RequestMapping(value = "/getSimpleGreat.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String getSimpleGreat(Integer sId,Integer dynamicId){
        Response response=new Response<>();
        if (sId != null&&dynamicId!=null){
            Great great = greatService.queryGreat(dynamicId, sId);
            if (great==null){
                response.setStatus(ResultCode.UNLIKE_STATUS.status());
                response.setCode(ResultCode.UNLIKE_STATUS.code());
            }else {
                response.setStatus(ResultCode.LIKE_STATUS.status());
                response.setCode(ResultCode.LIKE_STATUS.code());
            }
        }else {
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
        }
        return new Gson().toJson(response);
    }

}
