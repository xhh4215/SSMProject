package com.ssm.project.dao;

import com.ssm.project.BaseTest;
import com.ssm.project.entity.ProductCategory;
import com.ssm.project.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCategoryTest extends BaseTest {
    @Autowired
     ProdectCategoryDao prodectCategoryDao;
    @Test
    public  void testQueryShopCategory(){
        List<ProductCategory> areaList  = prodectCategoryDao.queryProductCategory(23l);
        assertEquals(3,areaList.size());

    }

}
