package com.ssm.project.services;

import com.ssm.project.dto.ShopExecution;
import com.ssm.project.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String filename);
}
