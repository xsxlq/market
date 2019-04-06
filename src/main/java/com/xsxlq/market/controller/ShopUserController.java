package com.xsxlq.market.controller;

import com.xsxlq.market.pojo.ShopUser;
import com.xsxlq.market.service.ShopUserService;
import com.xsxlq.market.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShopUserController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private ShopUserService shopUserService;

    @PostMapping("updateSendEmailStatus")
    public String updateSendEmailStatus(String id,String love, String say, Model model){
        logger.info("love:"+love+"&say:"+say);
        String loveStr = null;
        String sayStr = null;
        ShopUser shopUser = new ShopUser();
        if(love == null || "".equals(love)){
            shopUser.setLoveMsg(0);
            loveStr = "否";
        }else if("on".equals(love)){
            shopUser.setLoveMsg(1);
            loveStr = "是";
        }
        if(say == null || "".equals(say)){
            shopUser.setSayMsg(0);
            sayStr = "否";
        }else if("on".equals(say)){
            shopUser.setSayMsg(1);
            sayStr = "是";
        }
        try{
            shopUser.setId(Integer.valueOf(id));
            logger.info(shopUser.toString());
            if(shopUserService.updateByPrimaryKeySelective(shopUser) == 1){
                model.addAttribute("result","修改成功！修改状态已发送您邮箱。");
                //邮件发送较慢，开一个线程
                String finalSayStr = sayStr;
                String finalLoveStr = loveStr;
                new Thread(){
                    public void run(){
                        String subject = "【校园二手市场】";
                        String content = "【个人信息修改】您好，您已成功修改邮件提醒通知（留言提醒："+ finalSayStr +";收藏提醒："+ finalLoveStr +"）。";
                        mailUtil.sendText(shopUserService.selectById(shopUser.getId()).getEmail(),subject,content);
                    }
                }.start();
                return "market/success.html";
            }else{
                model.addAttribute("result","修改失败！");
                return "market/error.html";
            }
        }catch (Exception e){
            model.addAttribute("result",e.getMessage());
            return "market/error.html";
        }
    }
}
