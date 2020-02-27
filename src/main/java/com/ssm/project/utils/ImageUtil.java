package com.ssm.project.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/****
 * @author smallblack
 * @time 2020年2月27日
 * 项目的图片工具类
 */
public class ImageUtil {
    // 获取classpath
    public static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    // 日期格式化对象
    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    //随机数生成对象
    private static final Random random = new Random();

    /***
     * 将CommonsMultipartFile 转化为 File
     * @param commonsMultipartFile
     * @return  转化之后的File对象
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile commonsMultipartFile) {
        File newFile = new File(commonsMultipartFile.getOriginalFilename());
        try {
            commonsMultipartFile.transferTo(newFile);
        } catch (IllegalStateException e) {
            logger.error(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    //日志打印对象
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /***
     * 实现缩列图的功能
     * @param thumbnail
     * @param targetAddr :图片的存储位置
     * @return 返回图片的相对值路径
     */
    public static String generateThumbnail(File thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relative path is" + relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete path is" + PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail)
                    .size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /***
     * 创建目标路径涉及的到的目录 即home/work/xiaohei/xxxx.jpg
     * home work xiaohei 这三个文件夹要自动创建
     * @param targetAddr 返回的是目标的图片的存储的路径
     */
    private static void makeDirPath(String targetAddr) {
        String realFilePath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFilePath);
        if (dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /***
     * 获取输入文件的输入名
     * @param commonsMultipartFile
     * @return 返回的是文件的扩展名
     */
    private static String getFileExtension(File commonsMultipartFile) {
        String originalFileName = commonsMultipartFile.getName();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /***
     * 生成随机文件名， 当前时间精确到秒 +5位随机数
     * @return  返回的获取的随机的文件的名称
     */
    private static String getRandomFileName() {
        int randomNumber = random.nextInt(99999) + 10000;
        String nowTimeStr = mDateFormat.format(new Date());
        return nowTimeStr + randomNumber;
    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("/Users/smallblack/document/xiaopangzi.jpg")).size(200, 200)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                .outputQuality(0.8f).toFile(new File("/Users/smallblack/document/xiaopangzinew.jpg"));
    }
}
