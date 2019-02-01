package com.xsxlq.market.controller;

import org.springframework.ui.Model;
import com.xsxlq.market.pojo.ShopGoods;
import com.xsxlq.market.service.ShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ShopGoodsController {
    @Autowired
    private ShopGoodsService shopGoodsService;

    @RequestMapping("shopList")
    public List<ShopGoods> index(Model model){
        List<ShopGoods> list = shopGoodsService.selectAllGoods();
        model.addAttribute("shopList",list);
        return list;
    }
}
