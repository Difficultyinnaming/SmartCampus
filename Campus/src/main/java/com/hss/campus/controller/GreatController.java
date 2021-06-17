package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.dynamic.Great;
import com.hss.campus.service.dynamic.DynamicService;
import com.hss.campus.service.dynamic.GreatService;
import com.hss.campus.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreatController {

    @Autowired
    private GreatService greatService;
    @Autowired
    private DynamicService dynamicService;

    //查看点赞
    @RequestMapping(value = "/changeGreatStatus.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryGreat(Integer dynamicId, Integer sId) {
        Response<Integer> response = new Response<>();
        Great great = greatService.queryGreat(dynamicId, sId);
        System.out.println(great);
        return new Gson().toJson(response);
    }

    //评论列表
//    @RequestMapping(value = "/queryRecording.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
//    @ResponseBody
//    public String queryRecording(Integer id){
////        Response<List<Comment>> response=new Response<>();
////        if (id != null){
////            List<Comment> list=commentService.queryAll(id, OtherUtil.UPLOAD_MULTIPLE_PICTURES[0]);
////            for (Comment comment : list) {
////                Student student=service.queryNameAvatar(comment.getsId());
////                comment.setName(student.getName());
////                comment.setAvatar(OtherUtil.IP_ADDRESS+student.getAvatar());
////                response.setStatus(ResultCode.SUCCESS.status());
////                response.setCode(ResultCode.SUCCESS.code());
////                response.setData(list);
////            }
////        }else {
////            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
////            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
////        }
//        return new Gson().toJson(response);
//    }

}
