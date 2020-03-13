package com.ssm.project.dao;

import com.ssm.project.BaseTest;
import com.ssm.project.entity.ProductCategory;
import com.ssm.project.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCategoryTest extends BaseTest {
    @Autowired
     ProdectCategoryDao prodectCategoryDao;
    @Test
    @Ignore
    public  void testQueryShopCategory(){
        List<ProductCategory> areaList  = prodectCategoryDao.queryProductCategory(23l);
        assertEquals(3,areaList.size());

    }
    @Test
    @Ignore
    public void  batchInsertProductCategoryTest(){

        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setCreateTime(new Date());
        productCategory1.setShopId((24L));
        productCategory1.setPriority(1);
        productCategory1.setProductCategoryName("小小酥商品1");

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId((24L));
        productCategory2.setPriority(2);
        productCategory2.setProductCategoryName("小小酥商品2");


        ProductCategory productCategory3 = new ProductCategory();
        productCategory3.setCreateTime(new Date());
        productCategory3.setShopId((24L));
        productCategory3.setPriority(3);
        productCategory3.setProductCategoryName("小小酥商品3");


        ProductCategory productCategory4 = new ProductCategory();
        productCategory4.setCreateTime(new Date());
        productCategory4.setShopId((24L));
        productCategory4.setPriority(4);
        productCategory4.setProductCategoryName("小小酥商品4");

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory1);
        productCategoryList.add(productCategory2);
        productCategoryList.add(productCategory3);
        productCategoryList.add(productCategory4);

       int value =  prodectCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(4,value);


    }


    @Test
    public void testDeleteProductCategory(){
    List<ProductCategory> productCategoryList =  prodectCategoryDao.queryProductCategory(24l);
    for (ProductCategory productcategory:productCategoryList){
           if ("小小酥商品1".equals(productcategory.getProductCategoryName())
           || "小小酥商品2".equals(productcategory.getProductCategoryName())
           || "小小酥商品3".equals(productcategory.getProductCategoryName())
           || "小小酥商品4".equals(productcategory.getProductCategoryName())){
             int effectNumber =   prodectCategoryDao.deleteProductCategory(productcategory.getProductCategoryId(),24l);
              assertEquals(1,effectNumber);
           }
    }
    }

}
