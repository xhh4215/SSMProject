package com.ssm.project.dao;

import com.ssm.project.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProdectCategoryDao {
    List<ProductCategory> queryProductCategory(long shopId);

    /***
     * 批量新增商铺
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /***
     * 删除指定的商品类别
     * @param productCategoryId 指定商品id
     * @param shopId 商品id
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,@Param("shopId") long shopId );
}
