package com.ssm.project.services.impl;

import com.ssm.project.dao.ShopDao;
import com.ssm.project.dto.ShopExecution;
import com.ssm.project.entity.Shop;
import com.ssm.project.enums.ShopStateEnum;
import com.ssm.project.exception.ShopOperatorException;
import com.ssm.project.services.ShopService;
import com.ssm.project.utils.ImageUtil;
import com.ssm.project.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
@Service
public class ShopServiceImp implements ShopService {
    @Autowired
    private  ShopDao shopDao;
    @Override
    @Transactional //添加事务处理的标签  说明当前的操作需要事物处理
    /***
     * 添加店铺读数据库
     */
    public ShopExecution addShop(Shop shop, InputStream shopInpustream, String fileName) {
        // 数据的非空判断
        if (shop==null){
            return  new ShopExecution(ShopStateEnum.NULLSHOP);
        }
        try{
           // 初始化店铺的初始状态
           shop.setEnableStatus(0);
           shop.setCreateTime(new Date());
           shop.setLastEditTime(new Date());
           // 插入操作
           int effectNumber =shopDao.insertShop(shop);
           if (effectNumber<=0){
               throw new ShopOperatorException("店铺创建失败");
           }else{
               if (shopInpustream !=null){
                   //存储图片
                   try{
                       addShopImg(shop, shopInpustream,fileName);
                   }catch (Exception e){
                       throw new ShopOperatorException("图片添加失败"+e.getMessage());
                   }
                   //更新店铺图片地址
                   effectNumber = shopDao.updateShop(shop);
                   if (effectNumber<0){
                       throw new ShopOperatorException("更新图片地址失败");

                   }
               }
           }
          }catch (Exception e){
             throw new ShopOperatorException("addShop Error "+e.getMessage());
        }
        // 操作成功返回对应的数据
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    /***
     * 存储图片的同时更行shop的图片地址信息
     * @param shop  为修改图片信息的shop对象
     * @param shopInpustream  shopImg文件流
     */
    private void addShopImg(Shop shop, InputStream shopInpustream ,String fileName) {
        //获取图片存储子路径
       String targetAddr =  PathUtil.getShopImagePath(shop.getShopId());
        //通过传入的图片的子路径 和不同设别的主路径进行拼接存储
        //之后返回图片的相对路径
        String shopImgAddr = ImageUtil.generateThumbnail(shopInpustream,fileName,targetAddr);
        shop.setShopImg(shopImgAddr);
    }
}
