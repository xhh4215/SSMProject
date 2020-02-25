package com.ssm.project.services.impl;

import com.ssm.project.dao.AreaDao;
import com.ssm.project.entity.Area;
import com.ssm.project.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaServiceImp implements AreaService {
    // 通过spring在运行的时候将数据Dao自动注入
    @Autowired
    private AreaDao areaDao;
    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
