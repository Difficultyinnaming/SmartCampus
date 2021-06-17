package com.hss.campus.service.impl;

import com.hss.campus.dao.RepairImagesDao;
import com.hss.campus.entity.dynamic.Images;
import com.hss.campus.service.repair.RepairImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairImagesServiceImpl implements RepairImagesService {

    @Autowired
    private RepairImagesDao repairImagesDao;

    @Override
    public int add(String url, String mark) {
        return repairImagesDao.add(url,mark);
    }

    @Override
    public List<Images> queryUrl(String mark) {
        return repairImagesDao.queryUrl(mark);
    }
}
