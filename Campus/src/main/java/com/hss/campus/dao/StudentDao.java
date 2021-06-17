package com.hss.campus.dao;

import com.hss.campus.entity.user.Student;

public interface StudentDao {

    //保存新学生
    int save(Student student);
    //查找学生
    Student queryStudent(Integer id, String password);
    //查询学生姓名，头像
    Student queryNameAvatar(Integer id);
    //更新学生头像
    int updateAvatar(String avatar, Integer id);

}
