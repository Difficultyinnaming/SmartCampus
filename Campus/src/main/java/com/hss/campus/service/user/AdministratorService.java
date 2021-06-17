package com.hss.campus.service.user;

import com.hss.campus.entity.user.Administrator;

public interface AdministratorService {
    //管理员登录
    Administrator login(Integer id, String password);
    //更新头像
    int modifyAvatar(String avatar, Integer id);
    //删除公告
    int deleteItem(Integer id);

}
