package com.hss.campus.controller;

import com.google.gson.Gson;
import com.hss.campus.entity.dynamic.Comment;
import com.hss.campus.entity.dynamic.Dynamic;
import com.hss.campus.entity.user.Student;
import com.hss.campus.service.dynamic.CommentService;
import com.hss.campus.service.dynamic.DynamicService;
import com.hss.campus.service.user.StudentService;
import com.hss.campus.util.OtherUtil;
import com.hss.campus.util.Response;
import com.hss.campus.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private StudentService service;
    @Autowired
    private DynamicService dynamicService;

    //发表评论
    @RequestMapping(value = "/addComment.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String insertComment(@RequestBody Comment comment){
        Response<Comment> response=new Response<>();
        Integer dynamicId = comment.getDynamicId();
        Dynamic dynamic = dynamicService.queryDynamic(dynamicId);
        comment.setTime(OtherUtil.getNowTime("yyyy-MM-dd HH:mm"));
        comment.setState(OtherUtil.UPLOAD_MULTIPLE_PICTURES[0]);
        if (commentService.addComment(comment) != -1){
            if (dynamic.getCommentNum()!=null){
                dynamicService.changeCommentCountById(dynamic.getCommentNum()+1, dynamicId);
            }else {
                dynamicService.changeCommentCountById(1, dynamicId);
            }

            response.setStatus(ResultCode.SUCCESS.status());
            response.setCode(ResultCode.SUCCESS.code());
        }else {
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
        }
        return new Gson().toJson(response);
    }

    //评论列表
    @RequestMapping(value = "/queryComment.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String queryComment(Integer id){
        Response<List<Comment>> response=new Response<>();
        if (id != null){
            List<Comment> list=commentService.queryAll(id,OtherUtil.UPLOAD_MULTIPLE_PICTURES[0]);
            for (Comment comment : list) {
                Student student=service.queryNameAvatar(comment.getsId());
                comment.setName(student.getName());
                comment.setAvatar(OtherUtil.IP_ADDRESS+student.getAvatar());
                response.setStatus(ResultCode.SUCCESS.status());
                response.setCode(ResultCode.SUCCESS.code());
                response.setData(list);
            }
        }else {
            response.setStatus(ResultCode.USER_PARAMETER_ERROR.status());
            response.setCode(ResultCode.USER_PARAMETER_ERROR.code());
        }
        return new Gson().toJson(response);
    }

    //删除评论
    @RequestMapping(value = "/modifyComment.do",method = RequestMethod.POST,produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String modifyComment(Integer id) {
        Comment comment = commentService.queryOne(id);
        Integer dynamicId = comment.getDynamicId();
        Dynamic dynamic = dynamicService.queryDynamic(dynamicId);
        boolean change = dynamicService.changeCommentCountById(dynamic.getCommentNum() - 1, dynamicId);
        Response<Object> response = new Response<>();
        if (commentService.modifyState(id) != -1&&change){
            response.setCode(ResultCode.SUCCESS.code());
            response.setStatus(ResultCode.SUCCESS.status());
        }else {
            response.setCode(ResultCode.USER_TEXT_ERROR.code());
            response.setStatus(ResultCode.USER_TEXT_ERROR.status());
        }
        return new Gson().toJson(response);
    }
}
