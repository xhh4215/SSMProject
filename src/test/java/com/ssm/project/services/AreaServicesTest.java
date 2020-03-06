package com.ssm.project.services;

import com.ssm.project.BaseTest;
import com.ssm.project.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServicesTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Test
    public  void testGetAreaList(){
         List<Area> areaList   = areaService.getAreaList();
         assertEquals("哈尔滨",areaList.get(0).getAreaName());
    }
}
