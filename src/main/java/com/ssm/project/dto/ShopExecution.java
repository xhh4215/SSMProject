package com.ssm.project.dto;

import com.ssm.project.entity.Shop;
import com.ssm.project.enums.ShopStateEnum;

import java.util.List;

public class ShopExecution {
    //操作的shop
    private Shop shop;
    // shop列表
    private List<Shop> shopList;
    // 结果标识
    private int state;
    // 状态标识
    private String stateInfo;
    //操作的店铺的数量
    private int count;
    public ShopExecution() {

    }

    /***
     * 店铺操作失败的时候使用的构造器
     * @param stateEnum
     */
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();

    }
    /***
     * 店铺操作成功的时候使用的构造器
     * @param stateEnum
     */
    public ShopExecution(ShopStateEnum stateEnum,Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop =shop;

    } /***
     * 店铺操作成功的时候使用的构造器
     * @param stateEnum
     */
    public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList =shopList;

    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



}
