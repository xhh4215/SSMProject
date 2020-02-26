package com.ssm.project.dao;

import com.ssm.project.entity.Shop;

public interface ShopDao {
    /***
     * 新增店铺
     * @param shop  店铺信息
     * @return  增加的店铺数
     */
    int insertShop(Shop shop);
    /***
     * 更新店铺
     * @param shop  店铺信息
     * @return  增加的店铺数
     */
    int updateShop(Shop shop);
}
