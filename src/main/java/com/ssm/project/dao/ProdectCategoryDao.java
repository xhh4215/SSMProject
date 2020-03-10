package com.ssm.project.dao;

import com.ssm.project.entity.ProductCategory;

import java.util.List;

public interface ProdectCategoryDao {
    List<ProductCategory> queryProductCategory(long shopId);
}
