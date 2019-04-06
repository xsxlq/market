package com.xsxlq.market.util;

import com.xsxlq.market.controller.UploadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传工具类
 * 2_文件名
 * 1_上传成功
 * 0_上传失败
 *
 */
public class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    //公共上传参数
    public static final String SAVEPATH = "F:\\upload\\image\\";
    public static final String FILETYPE = "image";
    public static final Integer FILESIZE = 2; //单位：M

    public static Map<String,String> upload(@RequestParam("file") MultipartFile file, String savaPath,String fileType,int fileSize){
        Map<String,String> returnMap = new HashMap<>();
        returnMap.put("respCode","0");
        if (file.isEmpty()) {
            returnMap.put("respDesc","文件为空！");
            return returnMap;
        }
        if(file.getContentType().indexOf("image") == -1){
            returnMap.put("respDesc","文件类型错误！:"+file.getContentType());
            return returnMap;
        }
        if(file.getSize() > 1024*1024*fileSize || file.getSize() <= 0){
            returnMap.put("respDesc","文件大小不符合(0-"+fileSize+"M)"+file.getSize());
            return returnMap;
        }
        String fileName = file.getOriginalFilename();
        String fileLastName = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
        String filePath = savaPath;
        String newFileName = System.currentTimeMillis()+"."+fileLastName;
        File dest = new File(filePath + newFileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            returnMap.put("respCode","1");
            returnMap.put("respDesc","上传成功!");
            returnMap.put("respNewFileName",newFileName);
            return returnMap;
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        returnMap.put("respCode","0");
        returnMap.put("respDesc","未知错误，请check!");
        return returnMap;
    }
}
