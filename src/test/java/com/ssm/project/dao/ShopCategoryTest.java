package com.ssm.project.dao;

import com.ssm.project.BaseTest;
import com.ssm.project.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryTest extends BaseTest {
    @Autowired
    ShopCategoryDao shopCategoryDao;
    @Test
    public  void testQueryShopCategory(){
        List<ShopCategory> areaList  = shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals(1,areaList.size());

    }
}
