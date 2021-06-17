package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.user.Administrator;
import com.hss.campus.entity.user.Announcement;
import com.hss.campus.entity.util.ImageResponse;
import com.hss.campus.service.AnnouncementService;
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
import java.util.Date;
import java.util.List;

//公告
@Controller
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private AdministratorService service;

    //上传图片
    @RequestMapping(value = "/addAnnImageServer.do",method = RequestMethod.POST)
    @ResponseBody
    public String updateImage(MultipartFile fileName) throws IOException {
        Response<ImageResponse> response=new Response<>();
        ImageResponse imageResponse = FileUtil.uploadAPicture("announcement\\",fileName);
        if (!"".equals(imageResponse.getFileName())){
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(new ImageResponse(OtherUtil.IP_ADDRESS+"image/announcement/"+imageResponse.getFileName()));
        }else {
            response.setCode(ResultCode.USER_LOGIN_ERROR.code());
            response.setStatus(ResultCode.USER_AVATAR_ERROR.status());
        }
        return new Gson().toJson(response);
    }
    //上传公告
    @RequestMapping(value = "/getAnnouncement.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String announcement(@RequestBody Announcement announcement){
        Response<Announcement> response = new Response<>();
        if (announcement != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strTime = sdf.format(new Date(System.currentTimeMillis()));
            announcement.setTime(strTime);
            String image = announcement.getImage();
            if (!"".equals(image)){
                String url = OtherUtil.cutImageUrl(announcement.getImage());
                if (!"".equals(url)) {
                    announcement.setImage(url);
                }
            }else {
                announcement.setImage("image/announcement/avatar_default.png");
            }

            if (announcementService.add(announcement) != -1) {
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

    //管理员查看公告
    @RequestMapping(value = "/viewAnnouncementAdmin.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String viewAdministrator(@RequestBody Administrator administrator){
        Response<List<Announcement>> response=new Response<>();
        if (administrator.getId() != null){
            List<Announcement> list = announcementService.queryAdministrator(administrator.getId());
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
            response.setData(OtherUtil.updateImageUrl(list));
        }else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_TEXT_ERROR.status());
        }
        return new Gson().toJson(response);
    }
    //学生查看公告
    @RequestMapping(value = "/viewAnnouncementStu.do",method = RequestMethod.GET,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String ViewStudent(){
        Response<List<Announcement>> response = new Response<>();
        List<Announcement> list = announcementService.queryStudent();
        response.setCode(ResultCode.SUCCESS.code());
        response.setStatus(ResultCode.SUCCESS.status());
        response.setData(OtherUtil.updateImageUrl(list));
        return new Gson().toJson(response);
    }

    //删除公告
    @RequestMapping(value = "/deleteAnnouncement.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String deleteAnnouncement(@RequestBody Announcement announcement){
        Response<Integer> response=new Response<>();
        if (announcement.getId() != null){
            response.setCode(ResultCode.DELETE.code());
            response.setStatus(ResultCode.DELETE.status());
            response.setData(service.deleteItem(announcement.getId()));
        }else {
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
            response.setStatus(ResultCode.USER_TEXT_ERROR.status());
        }
        return new Gson().toJson(response);
    }
}
