package com.hss.campus.service.dynamic;

import com.hss.campus.entity.dynamic.Images;

import java.util.List;

public interface ImagesService {
    int add(String url, String mark);
    //查找
    List<Images> queryUrl(String mark);

    int modifyStatus(Integer status);
}
