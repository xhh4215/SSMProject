package com.ssm.project.services.impl;

import com.ssm.project.dao.ProdectCategoryDao;
import com.ssm.project.entity.ProductCategory;
import com.ssm.project.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProdectCategoryServiceImp implements ProductCategoryService {
    @Autowired
    ProdectCategoryDao prodectCategoryDao;
    @Override
    public List<ProductCategory> getProdectCategoryList(Long shopId) {
        return  prodectCategoryDao.queryProductCategory(shopId);
    }
}
