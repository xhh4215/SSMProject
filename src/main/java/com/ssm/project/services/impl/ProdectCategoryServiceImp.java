package com.ssm.project.services.impl;

import com.ssm.project.dao.ProdectCategoryDao;
import com.ssm.project.dto.ProductCategoryExecution;
import com.ssm.project.entity.ProductCategory;
import com.ssm.project.enums.ProductCategoryStateEnum;
import com.ssm.project.exception.ProductCategoryOperatorException;
import com.ssm.project.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdectCategoryServiceImp implements ProductCategoryService {
    @Autowired
    ProdectCategoryDao prodectCategoryDao;

    /***
     * 获取商品列表
     * @param shopId
     * @return
     */
    @Override
    public List<ProductCategory> getProdectCategoryList(Long shopId) {
        return prodectCategoryDao.queryProductCategory(shopId);
    }

    /***
     * 批量添加商品
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperatorException
     */
    @Override
    public ProductCategoryExecution batchInsertProdectCategory(List<ProductCategory> productCategoryList)
      throws  ProductCategoryOperatorException{
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
              int batchnum = prodectCategoryDao.batchInsertProductCategory(productCategoryList);
              if (batchnum<=0){
                  throw  new ProductCategoryOperatorException("店铺添加失败");
              }else{
               return  new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS,productCategoryList);
              }
            } catch (Exception e) {
                throw new ProductCategoryOperatorException(e.getMessage());
            }
        }else{
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }

    }

    /***
     * 删除商品 将此类别下的商品里的类别ID置为空
     * @param productCategoryId
     * @param shopId
     * @return
     */
    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)throws ProductCategoryOperatorException {
             // 删除商品 将此类别下的商品里的类别ID置为空
         try{
             int effectNum = prodectCategoryDao.deleteProductCategory(productCategoryId,shopId);
             if (effectNum<=0){
                 throw new ProductCategoryOperatorException("商品类别删除失败");
             }else{
                 return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
             }
         }catch (Exception e){
             throw  new ProductCategoryOperatorException("deleteProductCategory error"+e.getMessage());
         }
    }
}
