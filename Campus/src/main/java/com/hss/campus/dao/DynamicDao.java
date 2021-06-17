package com.hss.campus.dao;

import com.hss.campus.entity.dynamic.Dynamic;

import java.util.List;

public interface DynamicDao {

    int add(Dynamic dynamic);
    Dynamic queryDynamic(Integer id);
    List<Dynamic> queryAll(Integer id, Integer status);
    List<Dynamic> queryEveryone(Integer status);
    //删除动态
    int modifyStatus(Integer id);
    //修改点赞数
    int modifyNum(Integer num, Integer id);
    Long queryGreatNUm(Integer id);
    int changeCommentCountById(Integer count,Integer id);
}
