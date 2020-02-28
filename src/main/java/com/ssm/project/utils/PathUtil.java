package com.ssm.project.utils;

/***
 * @author 栾桂明
 * @time 2020年 2月 27日
 * 项目的路径工具类
 */
public class PathUtil {
    //获取系统的分隔符
    private static String seperator = System.getProperty("file.separator");


    /***
     * 获取项目图片的跟路径
     * @return
     */
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath ="";
        if (os.toLowerCase().startsWith("win")){
            basePath = "D:/projectdev/image";
        }else{
           basePath = "/Users/smallblack/document/UploadImage/";
        }
        basePath = basePath.replace("/",seperator);
        return  basePath;
    }

    /***
     * 返回不同的项目的子路径
     * @param shopId
     * @return
     */
    public static String getShopImagePath(long shopId){
         String imagePath = "upload/item/shop"+shopId+"/";
         return imagePath.replace("/",seperator);
    }
}
