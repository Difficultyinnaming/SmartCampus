package com.hss.campus.dao;

import com.hss.campus.entity.user.Administrator;

public interface AdministratorDao {
    //管理员登录
    Administrator queryAdministrator(Integer id, String password);
    //更新头像
    int updateAvatar(String avatar, Integer id);
    //删除公告
    int deleteItem(Integer id);
}
