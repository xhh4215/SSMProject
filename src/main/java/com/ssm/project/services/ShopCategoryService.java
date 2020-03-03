package com.ssm.project.services;

import com.ssm.project.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    List<ShopCategory>  getShopCategoryList(ShopCategory shopCategory);
}
