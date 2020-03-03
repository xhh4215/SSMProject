package com.ssm.project.services.impl;

import com.ssm.project.dao.ShopCategoryDao;
import com.ssm.project.entity.ShopCategory;
import com.ssm.project.services.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopCategoryServiceImp implements ShopCategoryService {
    @Autowired
    ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
        return shopCategoryDao.queryShopCategory(shopCategory);
    }
}
