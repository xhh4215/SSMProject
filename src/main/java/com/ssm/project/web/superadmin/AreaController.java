package com.ssm.project.web.superadmin;

import com.ssm.project.entity.Area;
import com.ssm.project.services.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")  //当前controller映射的路径地址
public class AreaController {
    Logger logger  = LoggerFactory.getLogger(AreaController.class);
    @Autowired //在需要当前类的时候spring会自动将实现了注入
    private AreaService areaService;
    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    @ResponseBody // 将结果以json的格式返回给前端
    private Map<String,Object> listArea(){
        logger.info("==== start ====");
        long startTime = System.currentTimeMillis();
        Map<String,Object> modelMap = new HashMap<>();
        List<Area> list = new ArrayList<>();
         try{
             list = areaService.getAreaList();
             modelMap.put("rows",list);
             modelMap.put("total",list.size());
         }catch (Exception e){
             e.printStackTrace();
             modelMap.put("success",false);
             modelMap.put("errMsg",e.toString());

         }
         logger.error("test error!");
        long endTime = System.currentTimeMillis();
        logger.debug("costTime[{}ms]",endTime-startTime);
        logger.info("==== end ====");
        return modelMap;
    }

}
