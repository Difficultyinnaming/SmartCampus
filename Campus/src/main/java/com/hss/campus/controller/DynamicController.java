package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.dynamic.Dynamic;
import com.hss.campus.entity.dynamic.Images;
import com.hss.campus.entity.user.Student;
import com.hss.campus.entity.util.ImageHeader;
import com.hss.campus.service.dynamic.DynamicService;
import com.hss.campus.service.dynamic.ImagesService;
import com.hss.campus.service.user.StudentService;
import com.hss.campus.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class DynamicController {

    @Autowired
    private ImagesService imagesService;
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private StudentService service;

    //插入图片
    @RequestMapping(value = "/uploadMultipleImg.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String uploadMultipleImg(@RequestParam MultipartFile[] fileName) throws InterruptedException {
        Response<ImageHeader> response=new Response<>();
        ImageHeader imageHeader=new ImageHeader();
        List<String> list = FileUtil.uploadMultiple("dynamic\\",fileName);
        String strUUID= UUID.randomUUID().toString().replace("-", "").toUpperCase();
        for (String s : list) {
            imagesService.add(OtherUtil.cutImageUrl(s),strUUID);
        }
        imageHeader.setFileName(strUUID);
        response.setCode(ResultCode.SUCCESS.code());
        response.setStatus(ResultCode.SUCCESS.status());
        response.setData(imageHeader);
        return new Gson().toJson(response);
    }
    //发布动态
    @RequestMapping(value = "/releaseDynamic.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String releaseDynamic(@RequestBody Dynamic dynamic)  {
        Response<List<Dynamic>> response = new Response<>();
        if (dynamic != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strTime = sdf.format(new Date(System.currentTimeMillis()));
            dynamic.setTime(strTime);
            dynamic.setStatus(OtherUtil.UPLOAD_MULTIPLE_PICTURES[0]);
            if (dynamicService.add(dynamic) != -1) {
                response.setCode(ResultCode.SUCCESS.code());
                response.setStatus(ResultCode.SUCCESS.status());
            }else {
                response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
                response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
            }
        } else {
            response.setCode(ResultCode.USER_TEXT_ERROR.code());
            response.setStatus(ResultCode.USER_TEXT_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //查看动态
    @RequestMapping(value = "/queryDynamic.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryDynamic(Integer id) {
        Response<List<Dynamic>> response = new Response<>();
        List<Dynamic> dynamicList = dynamicService.queryAll(id,OtherUtil.UPLOAD_MULTIPLE_PICTURES[0]);
        if (dynamicList != null){
            for (Dynamic dynamic : dynamicList) {
                Student student = service.queryNameAvatar(id);
                dynamic.setName(student.getName());
                dynamic.setAvatar(OtherUtil.IP_ADDRESS+student.getAvatar());
                List<Images> imagesList = imagesService.queryUrl(dynamic.getMark());
                for (Images images : imagesList) {
                    images.setUrl(OtherUtil.IP_ADDRESS+images.getUrl());
                }
                dynamic.setImagesList(imagesList);
            }
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(dynamicList);
        }else {
            response.setCode(ResultCode.USER_TEXT_ERROR.code());
            response.setStatus(ResultCode.USER_TEXT_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //查看所有动态
    @RequestMapping(value = "/queryEveryone.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryEveryone() {
        Response<List<Dynamic>> response = new Response<>();
        List<Dynamic> dynamicList = dynamicService.queryEveryone(OtherUtil.UPLOAD_MULTIPLE_PICTURES[0]);
        if (dynamicList != null){
            for (Dynamic dynamic : dynamicList) {
                Student student = service.queryNameAvatar(dynamic.getsId());
                dynamic.setName(student.getName());
                dynamic.setAvatar(OtherUtil.IP_ADDRESS+student.getAvatar());
                List<Images> imagesList = imagesService.queryUrl(dynamic.getMark());
                for (Images images : imagesList) {
                    images.setUrl(OtherUtil.IP_ADDRESS+images.getUrl());
                }
                dynamic.setImagesList(imagesList);
            }
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(dynamicList);
        }else {
            response.setCode(ResultCode.USER_TEXT_ERROR.code());
            response.setStatus(ResultCode.USER_TEXT_ERROR.status());
        }
        return new Gson().toJson(response);
    }

    //删除动态
    @RequestMapping(value = "/modifyDynamic.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String modifyDynamic(Integer id) {
        Response<Object> response = new Response<>();
        if (dynamicService.modifyStatus(id) != -1){
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
        }else {
            response.setCode(ResultCode.USER_TEXT_ERROR.code());
            response.setStatus(ResultCode.USER_TEXT_ERROR.status());
        }
        return new Gson().toJson(response);
    }
    //删除动态
    @RequestMapping(value = "/querySimpleOneDynamic.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String querySimpleOneDynamic(Integer dynamicId) {
        Response<Dynamic> response = new Response<>();
        if (dynamicId!=null){
            Dynamic dynamic = dynamicService.queryDynamic(dynamicId);
            if (dynamic!=null){
                Student student = service.queryNameAvatar(dynamic.getsId());
                dynamic.setAvatar(OtherUtil.IP_ADDRESS+student.getAvatar());
                dynamic.setName(student.getName());
                List<Images> imagesList = imagesService.queryUrl(dynamic.getMark());
                for (int i = 0; i < imagesList.size(); i++) {
                    Images images = imagesList.get(i);
                    images.setUrl(OtherUtil.IP_ADDRESS+images.getUrl());
                    imagesList.set(i,images);
                }
                dynamic.setImagesList(imagesList);
                response.setCode(ResultCode.SUCCESS.code());
                response.setStatus(ResultCode.SUCCESS.status());
                response.setData(dynamic);
            }else {
                response.setCode(ResultCode.USER_TEXT_ERROR.code());
                response.setStatus(ResultCode.USER_TEXT_ERROR.status());
            }
        }else {
            response.setCode(ResultCode.USER_TEXT_ERROR.code());
            response.setStatus(ResultCode.USER_TEXT_ERROR.status());
        }
        return new Gson().toJson(response);
    }

}
