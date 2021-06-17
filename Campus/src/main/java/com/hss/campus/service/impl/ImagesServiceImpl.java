package com.hss.campus.service.impl;

import com.hss.campus.dao.ImagesDao;
import com.hss.campus.entity.dynamic.Images;
import com.hss.campus.service.dynamic.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    private ImagesDao imagesDao;

    @Override
    public List<Images> queryUrl(String mark) {
        return imagesDao.queryUrl(mark);
    }

    @Override
    public int modifyStatus(Integer status) {
        return imagesDao.modifyStatus(status);
    }

    @Override
    public int add(String url,String mark) {
        return imagesDao.add(url,mark);
    }
}
