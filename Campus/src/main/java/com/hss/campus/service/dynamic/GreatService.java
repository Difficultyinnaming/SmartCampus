package com.hss.campus.service.dynamic;

import com.hss.campus.entity.dynamic.Great;

public interface GreatService {
    Great queryGreat(Integer dynamicId, Integer sId);
    boolean deleteRecording(Integer id);

    int addRecording(Great great);
}
