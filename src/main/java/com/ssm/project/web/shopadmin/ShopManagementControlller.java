package com.ssm.project.web.shopadmin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.project.dto.ShopExecution;
import com.ssm.project.entity.Area;
import com.ssm.project.entity.PersonInfo;
import com.ssm.project.entity.Shop;
import com.ssm.project.entity.ShopCategory;
import com.ssm.project.enums.ShopStateEnum;
import com.ssm.project.exception.ShopOperatorException;
import com.ssm.project.services.AreaService;
import com.ssm.project.services.ShopCategoryService;
import com.ssm.project.services.ShopService;
import com.ssm.project.utils.CodeUtil;
import com.ssm.project.utils.HttpServletRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementControlller {
    Logger logger = LoggerFactory.getLogger(ShopManagementControlller.class);
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @ResponseBody
    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    /**
     * 注册商店
     * @param HttpServletRequest 封装http请求的全部信息的对象
     */
    private Map<String, Object> registerShop(HttpServletRequest request) {
        logger.debug("商铺注册开始");
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success", false);
            modelMap.put("errorMsg", "输入验证码有错");
            return modelMap;
        }
        // 1 接收并转化相应的信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", e.getMessage());
            return modelMap;
        }
        // 一个spring自带的文件工具类
        CommonsMultipartFile shopImg = null;
        // CommonsMultipartFile解析对象
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断是否有文件上传流
        if (commonsMultipartResolver.isMultipart(request)) {
            // 将请求转化为 MultipartHttpServletRequest
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            // 获取请求中的文件 CommonsMultipartFile
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "上传图片不能为空");
            return modelMap;
        }
        // 2 注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = new PersonInfo();
            // session TODO
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution shopExecution;
            try {
                shopExecution = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);

                } else {
                    modelMap.put("success", false);
                    modelMap.put("errorMsg", shopExecution.getStateInfo());
                }
            } catch (ShopOperatorException e) {
                modelMap.put("success", false);
                modelMap.put("errorMsg", e.getMessage());
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errorMsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "请输入店铺信息");
            return modelMap;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/getshopinitinfo",method = RequestMethod.GET)
    private Map<String,Object> getShopInitInfo(){
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory>  shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try{
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("errMsg",e.getMessage());
            modelMap.put("success",false);
        }
         return  modelMap;
    }


}
