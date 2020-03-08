package com.ssm.project.dao;

import com.ssm.project.BaseTest;
import com.ssm.project.entity.Area;
import com.ssm.project.entity.PersonInfo;
import com.ssm.project.entity.Shop;
import com.ssm.project.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;
    @Test
    public void testQueryByShopId(){
        long shopid = 27l;
        Shop shop = shopDao.queryByShopId(shopid);
        System.out.print(shop.getArea().getAreaId());
        System.out.print(shop.getArea().getAreaName());
    }
    @Test
    @Ignore
    public void testInsertShop(){
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
        shop.setShopName("德源苑");
        shop.setShopDesc("test");
        shop.setShopAddr("山西大学商务学院");
        shop.setPhone("19969692732");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectNUmber = shopDao.insertShop(shop);
        assertEquals(effectNUmber,1);
    }
    @Test
    @Ignore
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(23l);
        shop.setShopDesc("一个你总去的餐厅");
        shop.setShopAddr("山西大学商务学院森伯10 旁边");
        shop.setLastEditTime(new Date());
        int effectNUmber  = shopDao.updateShop(shop);
        assertEquals(effectNUmber,1);
    }
}
