package com.hss.campus.service.impl;

import com.hss.campus.dao.StudentDao;
import com.hss.campus.entity.user.Student;
import com.hss.campus.service.user.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    //学生登录
    @Override
    public Student login(Integer id, String password) {
        return studentDao.queryStudent(id,password);
    }
    //添加学生
    @Override
    public int addStudent(Student student) {
        return studentDao.save(student);
    }
    //更新学生头像
    @Override
    public int modifyAvatar(String avatar,Integer id) {
        return studentDao.updateAvatar(avatar, id);
    }

    @Override
    public Student queryNameAvatar(Integer id) {
        return studentDao.queryNameAvatar(id);
    }

}
