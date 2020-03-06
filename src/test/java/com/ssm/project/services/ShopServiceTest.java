package com.ssm.project.services;

import com.ssm.project.BaseTest;
import com.ssm.project.dto.ShopExecution;
import com.ssm.project.entity.Area;
import com.ssm.project.entity.PersonInfo;
import com.ssm.project.entity.Shop;
import com.ssm.project.entity.ShopCategory;
import com.ssm.project.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private  ShopService shopService;
    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo ower = new PersonInfo();
        ower.setUserId(1L);
        Area area = new Area();
        area.setAreaId(1);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(ower);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("德源苑3");
        shop.setShopDesc("test3");
        shop.setShopAddr("山西大学商务学院3");
        shop.setPhone("19969692732");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File img = new File("/Users/smallblack/document/xiaopangzi.jpg");
         InputStream  inputStream = new FileInputStream(img);
         ShopExecution shopExecution = shopService.addShop(shop,inputStream,img.getName());
        assertEquals(ShopStateEnum.CHECK.getState(),shopExecution.getState());

    }
}
