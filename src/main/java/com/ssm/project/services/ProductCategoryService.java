package com.ssm.project.services;

import com.ssm.project.dao.ProdectCategoryDao;
import com.ssm.project.entity.ProductCategory;
import com.ssm.project.entity.ShopCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getProdectCategoryList(Long shopId);
}
