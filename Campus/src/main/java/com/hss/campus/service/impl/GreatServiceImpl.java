package com.hss.campus.service.impl;

import com.hss.campus.dao.GreatDao;
import com.hss.campus.entity.dynamic.Great;
import com.hss.campus.service.dynamic.GreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreatServiceImpl implements GreatService {

    @Autowired
    private GreatDao greatDao;

    @Override
    public Great queryGreat(Integer dynamicId, Integer sId) {
        return greatDao.queryGreat(dynamicId,sId);
    }

    @Override
    public boolean deleteRecording(Integer id) {
        return greatDao.deleteRecording(id);
    }

    @Override
    public int addRecording(Great great) {
        return greatDao.addRecording(great);
    }
}
