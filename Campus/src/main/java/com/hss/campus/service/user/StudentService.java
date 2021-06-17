package com.hss.campus.service.user;

import com.hss.campus.entity.user.Student;

public interface StudentService {
    //学生登录
    Student login(Integer id, String password);
    //添加学生
    int addStudent(Student student);
    //更新学生头像
    int modifyAvatar(String avatar, Integer id);
    //查询学生姓名，头像
    Student queryNameAvatar(Integer id);
}
