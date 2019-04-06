package com.xsxlq.market.controller.admin;

import com.xsxlq.market.dao.ShopGoodsTypeMapper;
import com.xsxlq.market.pojo.*;
import com.xsxlq.market.service.GoodsSayService;
import com.xsxlq.market.service.ShopGoodsService;
import com.xsxlq.market.service.UserGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminGoodsController {
    @Autowired
    private ShopGoodsService shopGoodsService;

    @Autowired
    private ShopGoodsTypeMapper shopGoodsTypeMapper;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private GoodsSayService goodsSayService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/x-admin/goodsinfo.json")
    @ResponseBody
    public Map<String,Object> userInfo(String page, String limit, ShopGoods shopGoods){
        int pageNum = Integer.valueOf(page);
        int limitNum = Integer.valueOf(limit);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("code",0);
        returnMap.put("msg","ok");
        List<ShopGoods> goodsList = shopGoodsService.selectiveGoods(null,shopGoods);
        List<ShopGoods> goodsListPage = new ArrayList<>();
        if(goodsList.size() == 0){
            returnMap.put("code",1);
            returnMap.put("msg","未查询到数据");
            returnMap.put("count",0);
        }else{
            returnMap.put("count",goodsList.size());
        }
        int i = 1;
        for (ShopGoods s: goodsList
        ) {
            if(i < (pageNum * limitNum) && i>(pageNum - 1)*limitNum){
                goodsListPage.add(s);
            }
            i++;
        }
        returnMap.put("data",goodsListPage);
        return returnMap;
    }

    @RequestMapping("/x-admin/goodstype.json")
    @ResponseBody
    public Map<String,Object> goodsType(String page, String limit, ShopGoodsType goodsType){
        int pageNum = Integer.valueOf(page);
        int limitNum = Integer.valueOf(limit);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("code",0);
        returnMap.put("msg","ok");
        List<ShopGoodsType> goodsTypeList = shopGoodsTypeMapper.selectAll();
        List<ShopGoodsType> goodsTypeListPage = new ArrayList<>();
        if(goodsTypeList.size() == 0){
            returnMap.put("code",1);
            returnMap.put("msg","未查询到数据");
            returnMap.put("count",0);
        }else{
            returnMap.put("count",goodsTypeList.size());
        }
        int i = 1;
        for (ShopGoodsType s: goodsTypeList
        ) {
            if(i < (pageNum * limitNum) && i>(pageNum - 1)*limitNum){
                goodsTypeListPage.add(s);
            }
            i++;
        }
        returnMap.put("data",goodsTypeListPage);
        return returnMap;
    }

    @RequestMapping("/x-admin/usergoods.json")
    @ResponseBody
    public Map<String,Object> userGoods(String page, String limit, UserGoods userGoods){
        int pageNum = Integer.valueOf(page);
        int limitNum = Integer.valueOf(limit);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("code",0);
        returnMap.put("msg","ok");
        List<UserGoods> userGoodsList = userGoodsService.selectAll(userGoods);
        List<UserGoods> userGoodsListPage = new ArrayList<>();
        if(userGoodsList.size() == 0){
            returnMap.put("code",1);
            returnMap.put("msg","未查询到数据");
            returnMap.put("count",0);
        }else{
            returnMap.put("count",userGoodsList.size());
        }
        int i = 1;
        for (UserGoods s: userGoodsList
        ) {
            if(i < (pageNum * limitNum) && i>(pageNum - 1)*limitNum){
                userGoodsListPage.add(s);
            }
            i++;
        }
        returnMap.put("data",userGoodsListPage);
        return returnMap;
    }

    @RequestMapping("/x-admin/goodsSay.json")
    @ResponseBody
    public Map<String,Object> goodsSay(String page, String limit, GoodsSay goodsSay){
        int pageNum = Integer.valueOf(page);
        int limitNum = Integer.valueOf(limit);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("code",0);
        returnMap.put("msg","ok");
        List<GoodsSay> goodsSayList = goodsSayService.selectALLSelective(goodsSay);
        List<GoodsSay> goodsSayListPage = new ArrayList<>();
        if(goodsSayList.size() == 0){
            returnMap.put("code",1);
            returnMap.put("msg","未查询到数据");
            returnMap.put("count",0);
        }else{
            returnMap.put("count",goodsSayList.size());
        }
        int i = 1;
        for (GoodsSay s: goodsSayList
        ) {
            if(i < (pageNum * limitNum) && i>(pageNum - 1)*limitNum){
                goodsSayListPage.add(s);
            }
            i++;
        }
        returnMap.put("data",goodsSayListPage);
        return returnMap;
    }

    @RequestMapping("/x-admin/updateGoods")
    @ResponseBody
    public Map<String,String> updateUser(ShopGoods shopGoods) {
        Map<String, String> returnMap = new HashMap<>();
        String resCode = "0";
        String resDesc = "updateByPrimaryKeySelective(shopUser)---->0";
        try {
            if (shopGoodsService.updateByPrimaryKeySelective(shopGoods) == 1) {
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

    @RequestMapping("/x-admin/delGoodsSayById")
    @ResponseBody
    public Map<String,String> delGoodsSayById(String id) {
        Map<String, String> returnMap = new HashMap<>();
        String resCode = "0";
        String resDesc = "updateByPrimaryKeySelective(shopUser)---->0";
        try {
            if (goodsSayService.deleteByPrimaryKey(Integer.valueOf(id)) == 1) {
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
