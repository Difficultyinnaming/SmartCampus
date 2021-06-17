package com.hss.campus.service.dynamic;

import com.hss.campus.entity.dynamic.Dynamic;

import java.util.List;

public interface DynamicService {
    int add(Dynamic dynamic);
    Dynamic queryDynamic(Integer id);
    List<Dynamic> queryAll(Integer id, Integer status);
    List<Dynamic> queryEveryone(Integer status);
    int modifyStatus(Integer id);
    int modifyNum(Integer num, Integer id);

    Long queryGreatNUm(Integer id);
    boolean changeCommentCountById(Integer count,Integer id);
}
