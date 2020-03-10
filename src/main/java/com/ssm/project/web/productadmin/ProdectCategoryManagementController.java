package com.ssm.project.web.productadmin;

import com.ssm.project.dto.Result;
import com.ssm.project.entity.ProductCategory;
import com.ssm.project.entity.Shop;
import com.ssm.project.enums.ProductCategoryStateEnum;
import com.ssm.project.enums.ProductStateEnum;
import com.ssm.project.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
@RequestMapping("/shopadmin")
public class ProdectCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @ResponseBody
    @RequestMapping(value ="/getproductcategorylist",method = RequestMethod.GET)
    private Result<List<ProductCategory>> getProdectCategoryList(HttpServletRequest request){
       Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
       List<ProductCategory> productCategoryList = null;
       if (currentShop!=null&& currentShop.getShopId()>0){
          productCategoryList =  productCategoryService.getProdectCategoryList(currentShop.getShopId());
          return new Result<>(true,productCategoryList);
       }else{
           ProductCategoryStateEnum productCategoryStateEnum =  ProductCategoryStateEnum.INNER_ERROR;
           return new Result<>(false,productCategoryStateEnum.getStateInfo(),productCategoryStateEnum.getState());
       }

    }
}
