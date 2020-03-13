package com.ssm.project.dto;

import com.ssm.project.entity.ProductCategory;
import com.ssm.project.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    private int state;
    private String stateInfo;
    private List<ProductCategory> productCategoryList;
    public ProductCategoryExecution() {
    }

    public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum) {
        state = productCategoryStateEnum.getState();
        stateInfo = productCategoryStateEnum.getStateInfo();
    }

    public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum, List<ProductCategory> productCategoryList) {
        state = productCategoryStateEnum.getState();
        stateInfo = productCategoryStateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
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

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }



}
