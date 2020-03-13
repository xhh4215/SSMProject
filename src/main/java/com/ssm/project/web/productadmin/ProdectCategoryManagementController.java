package com.ssm.project.web.productadmin;

import com.ssm.project.dto.ProductCategoryExecution;
import com.ssm.project.dto.Result;
import com.ssm.project.entity.ProductCategory;
import com.ssm.project.entity.Shop;
import com.ssm.project.enums.ProductCategoryStateEnum;
import com.ssm.project.enums.ProductStateEnum;
import com.ssm.project.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ProdectCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @ResponseBody
    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    private Result<List<ProductCategory>> getProdectCategoryList(HttpServletRequest request) {
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> productCategoryList = null;
        if (currentShop != null && currentShop.getShopId() > 0) {
            productCategoryList = productCategoryService.getProdectCategoryList(currentShop.getShopId());
            return new Result<>(true, productCategoryList);
        } else {
            ProductCategoryStateEnum productCategoryStateEnum = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<>(false, productCategoryStateEnum.getStateInfo(), productCategoryStateEnum.getState());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
    private Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory productCategory : productCategoryList) {
            productCategory.setShopId(currentShop.getShopId());
        }
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                ProductCategoryExecution productCategoryExecution = productCategoryService.batchInsertProdectCategory(productCategoryList);
                if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", productCategoryExecution.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "至少我输入一个商品类别");
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
    private Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest request) {
        Map<String, Object> modelmap = new HashMap<>();
        if (productCategoryId != null && productCategoryId > 0) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution productCategoryExecution = productCategoryService.
                        deleteProductCategory(productCategoryId, currentShop.getShopId());

                if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelmap.put("success", true);
                } else {
                    modelmap.put("success", false);
                    modelmap.put("errMsg",productCategoryExecution.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelmap.put("success", false);
                modelmap.put("errMsg", e.toString());
                return modelmap;
            }
        } else {
            modelmap.put("success", false);
            modelmap.put("errMsg","至少选择一个商品类别");

        }
        return modelmap;
    }

}
