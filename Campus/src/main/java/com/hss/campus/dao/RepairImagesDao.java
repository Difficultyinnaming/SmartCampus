package com.hss.campus.dao;

import com.hss.campus.entity.dynamic.Images;

import java.util.List;

public interface RepairImagesDao {
    int add(String url, String mark);
    //查找
    List<Images> queryUrl(String mark);
}
