package com.hss.campus.service.repair;

import com.hss.campus.entity.dynamic.Images;

import java.util.List;

public interface RepairImagesService {
    int add(String url, String mark);
    //查找
    List<Images> queryUrl(String mark);
}
