package com.xsxlq.market.controller;

import com.xsxlq.market.pojo.ShopUserInfo;
import com.xsxlq.market.pojo.Suggest;
import com.xsxlq.market.service.ShopUserInfoService;
import com.xsxlq.market.service.SuggestService;
import com.xsxlq.market.util.FileUtil;
import com.xsxlq.market.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@Controller
public class UploadController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ShopUserInfoService shopUserInfoService;
    @Autowired
    private SuggestService suggestService;
    @Autowired
    private MailUtil mailUtil;
    /**
     * 上传用户头像
     * @param file
     */
    @PostMapping("/uploadIcon")
    public String uploadIcon(@RequestParam("file") MultipartFile file,@RequestParam("id") String id ,Model model){
        logger.debug("控制器：uploadIcon\n"+"upload id:"+id);
        if(id != null && !id.equals("")){
            int userId = Integer.valueOf(id);
            if(userId >= 0){
                Map<String,String> returnMap = upload(file,1);
                logger.debug(returnMap.toString());
                if(returnMap.get("respCode").equals("0")){
                    model.addAttribute("uploadIconResult",returnMap.get("respDesc"));
                }else if(returnMap.get("respCode").equals("1")){
                    //修改用户头像地址
                    String fileName = returnMap.get("respNewFileName");
                    String sqlFileUrl = "/upload/img/"+fileName;
                    ShopUserInfo userInfo = new ShopUserInfo();
                    userInfo.setIcon(sqlFileUrl);
                    userInfo.setId(userId);
                    if(shopUserInfoService.updateByPrimaryKeySelective(userInfo)){
                        model.addAttribute("uploadIconResult",returnMap.get("respDesc"));
                        logger.debug("添加成功");
                    }else{
                        logger.debug("添加失败！");
                        model.addAttribute("uploadIconResult","添加失败！");
                    }
                }
            }else{
                logger.debug("上传失败！");
                model.addAttribute("uploadIconResult","上传失败！");
            }
        }
        return "market/page/personal/personal_info";
    }

    /**
     * 上传方法
     * @param file  文件
     * @param fileSize  限制文件大小（单位：M）
     * @return 返回Map集合{respCode: 0 1}{respDesc 失败原因 成功：返回文件名}
     */
    public Map<String,String> upload(MultipartFile file,int fileSize) {
        //上传文件路径
        Map<String,String> returnMap = FileUtil.upload(file,FileUtil.SAVEPATH,FileUtil.FILETYPE,fileSize);
        return returnMap;
    }

    /**
     * 反馈与建议
     * @param file
     * @param suggest
     * @param model
     * @return
     */
    @PostMapping("suggest")
    public String suggest(@RequestParam("file") MultipartFile file, Suggest suggest, Model model){
        if(file.isEmpty() != true){
            Map<String,String> returnMap = upload(file,3);
            logger.debug(returnMap.toString());
            if(returnMap.get("respCode").equals("0")){
                //图片上传失败，不用返回到页面，打上日志，或邮到管理员或添加到错误表，不影响反馈的内容入表
//                model.addAttribute("result",returnMap.get("respDesc"));
//                return "market/error";
                logger.error("反馈时用户上传图片失败："+returnMap.get("respDesc"));
            }else if(returnMap.get("respCode").equals("1")){
                String fileName = returnMap.get("respNewFileName");
                String sqlFileUrl = "/upload/img/"+fileName;
                suggest.setImg(sqlFileUrl);
            }
        }
        logger.info("反馈："+suggest.toString());
        if(suggestService.insertSelective(suggest) == 1){
            model.addAttribute("result","您的问题已经收到，感谢您的反馈！");
            //添加线程，将反馈邮件到管理员（后期可以将反馈添加到数据库）
            new Thread(){
                public void run(){
                    //发送邮件
                    mailUtil.sendText("614171641@qq.com","【校园二手市场】","【用户反馈】"+suggest.toString());
                }
            }.start();
            return "market/success";
        }
        //入表失败
        model.addAttribute("result","反馈失败，反馈内容入表失败！");
        return "market/error";
    }
}
