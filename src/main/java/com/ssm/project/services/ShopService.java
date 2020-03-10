package com.ssm.project.services;

import com.ssm.project.dto.ShopExecution;
import com.ssm.project.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /***
     * 添加店铺
     * @param shop 添加的店铺信息
     * @param shopImgInputStream 获取店铺图片的文件流
     * @param filename 文件的名字
     * @return  返回的注册之后封装的shop信息
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String filename);

    /***
     * 更新店铺信息包括图片信息
     * @param shop
     * @param shopImgInputStream
     * @param filename
     * @return
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream,String filename);

    /***
     * 通过商店的id查找商店
     * @param shopId  商店id
     * @return  查找到的商店信息
     */
     Shop  getByShopId(long shopId);

    /***
     * 根据shopCondition分页返回列表信息
     * @param shopCondition  筛选添加
     * @param pageIndex
     * @param pageSize
     * @return
     */
     ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);


}
