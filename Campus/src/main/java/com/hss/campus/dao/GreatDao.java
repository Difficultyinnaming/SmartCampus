package com.hss.campus.dao;

import com.hss.campus.entity.dynamic.Great;

import java.util.List;

public interface GreatDao {
    Great queryGreat(Integer dynamicId, Integer sId);
    boolean deleteRecording(Integer id);
    int addRecording(Great great);
    List<Great> queryGreatRecord(Integer dynamicId);
}
