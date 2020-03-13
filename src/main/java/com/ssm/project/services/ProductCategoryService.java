package com.ssm.project.services;
import com.ssm.project.dto.ProductCategoryExecution;
import com.ssm.project.entity.ProductCategory;
import java.util.List;

public interface ProductCategoryService {
    /***
     * 获取商品列表
     * @param shopId
     * @return
     */
    List<ProductCategory> getProdectCategoryList(Long shopId);

    /***
     * 批量添加商品
     * @param productCategoryList
     * @return
     */
    ProductCategoryExecution batchInsertProdectCategory(List<ProductCategory> productCategoryList);

    /***
     * 删除商品
     * @param productCategoryId
     * @param shopId
     * @return
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId);
}
