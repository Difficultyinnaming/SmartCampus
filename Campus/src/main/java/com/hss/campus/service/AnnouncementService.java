package com.hss.campus.service;


import com.hss.campus.entity.user.Announcement;

import java.util.List;

public interface AnnouncementService {
    //插入公告
    int add(Announcement announcement);
    //管理员查看公告
    List<Announcement> queryAdministrator(Integer id);
    //学生查看公告
    List<Announcement> queryStudent();
}
