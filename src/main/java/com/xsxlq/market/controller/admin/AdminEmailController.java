package com.xsxlq.market.controller.admin;

import com.xsxlq.market.pojo.EmailInfo;
import com.xsxlq.market.service.EmailInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminEmailController {
    @Autowired
    private EmailInfoService emailInfoService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/x-admin/emailInfo.json")
    @ResponseBody
    public Map<String,Object> emailInfo(String page, String limit, EmailInfo emailInfo){
        int pageNum = Integer.valueOf(page);
        int limitNum = Integer.valueOf(limit);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("code",0);
        returnMap.put("msg","ok");
        List<EmailInfo> emailInfoList = emailInfoService.selectALLSelective(emailInfo);
        List<EmailInfo> emailInfoListPage = new ArrayList<>();
        if(emailInfoList.size() == 0){
            returnMap.put("code",1);
            returnMap.put("msg","未查询到数据");
            returnMap.put("count",0);
        }else{
            returnMap.put("count",emailInfoList.size());
        }
        int i = 1;
        for (EmailInfo s: emailInfoList
        ) {
            if(i < (pageNum * limitNum) && i>(pageNum - 1)*limitNum){
                emailInfoListPage.add(s);
            }
            i++;
        }
        returnMap.put("data",emailInfoListPage);
        return returnMap;
    }

    @RequestMapping("/x-admin/delEmailInfoById")
    @ResponseBody
    public Map<String,String> delEmailInfoById(String id) {
        Map<String, String> returnMap = new HashMap<>();
        String resCode = "0";
        String resDesc = "updateByPrimaryKeySelective(shopUser)---->0";
        try {
            if (emailInfoService.deleteByPrimaryKey(Integer.valueOf(id)) == 1) {
                resCode = "1";
                resDesc = "ok";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resCode = "0";
            resDesc = e.getMessage();
        }
        returnMap.put("resCode", resCode);
        returnMap.put("resDesc", resDesc);
        return returnMap;
    }
}
