package com.xsxlq.market.controller.admin;

import com.xsxlq.market.pojo.MyError;
import com.xsxlq.market.pojo.ShopGoods;
import com.xsxlq.market.service.MyErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminMyErrorController {

    @Autowired
    private MyErrorService myErrorService;

    @RequestMapping("/x-admin/error.json")
    @ResponseBody
    public Map<String,Object> myError(String page, String limit, MyError myError){
        int pageNum = Integer.valueOf(page);
        int limitNum = Integer.valueOf(limit);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("code",0);
        returnMap.put("msg","ok");
        List<MyError> errorList = myErrorService.selectAllSeletive(myError);
        List<MyError> eoorListPage = new ArrayList<>();
        if(errorList.size() == 0){
            returnMap.put("code",1);
            returnMap.put("msg","未查询到数据");
            returnMap.put("count",0);
        }else{
            returnMap.put("count",errorList.size());
        }
        int i = 1;
        for (MyError s: errorList
        ) {
            if(i < (pageNum * limitNum) && i>(pageNum - 1)*limitNum){
                eoorListPage.add(s);
            }
            i++;
        }
        returnMap.put("data",eoorListPage);
        return returnMap;
    }
}
