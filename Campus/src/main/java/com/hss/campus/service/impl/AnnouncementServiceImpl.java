package com.hss.campus.service.impl;

import com.hss.campus.dao.AnnouncementDao;
import com.hss.campus.entity.user.Announcement;
import com.hss.campus.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    //插入公告
    @Override
    public int add(Announcement announcement) {
        return announcementDao.add(announcement);
    }
    //管理员查看公告
    @Override
    public List<Announcement> queryAdministrator(Integer id) {
        return announcementDao.queryAdministrator(id);
    }
    //学生查看公告
    @Override
    public List<Announcement> queryStudent() {
        return announcementDao.queryStudent();
    }

}
