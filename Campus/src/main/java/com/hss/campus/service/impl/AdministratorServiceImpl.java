package com.hss.campus.service.impl;

import com.hss.campus.dao.AdministratorDao;
import com.hss.campus.entity.user.Administrator;
import com.hss.campus.service.user.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorDao administratorDao;

    //管理员登录
    @Override
    public Administrator login(Integer id, String password) {
        return administratorDao.queryAdministrator(id,password);
    }
    //更新头像
    @Override
    public int modifyAvatar(String avatar,Integer id) {
        return administratorDao.updateAvatar(avatar,id);
    }
    //删除公告
    @Override
    public int deleteItem(Integer id) {
        return administratorDao.deleteItem(id);
    }

}
