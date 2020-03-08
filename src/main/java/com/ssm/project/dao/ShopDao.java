package com.ssm.project.dao;

import com.ssm.project.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /***
     *  实现分页查询 可输入的条件有  商店名称 ， 店铺状态，店铺类别，区域Id，owner
     * @param shopCondition 查询条件
     * @param rowIndex 查询的开始位置
     * @param pageSize 查询的页数
     * @return 查询到的shop信息
     * @param的作用是指定在XML文件中去参数的区域
     */
    List<Shop>  queryShopList(@Param("shopCondition") Shop shopCondition,@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);
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
    /****
     * 通过shopId查找商店的信息
     * @param shopId
     * @return 返回获取到的商铺信息
     */
    Shop queryByShopId(long shopId);
    /***
     * 返回queryShopList总数
     * @param shopCondition  获取的调教
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);


}
