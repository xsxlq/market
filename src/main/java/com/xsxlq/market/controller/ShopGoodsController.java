package com.xsxlq.market.controller;

import com.github.pagehelper.PageInfo;
import com.xsxlq.market.pojo.*;
import com.xsxlq.market.service.*;
import com.xsxlq.market.util.ExceptionUtil;
import com.xsxlq.market.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class ShopGoodsController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ShopGoodsService shopGoodsService;

    @Autowired
    private LoveService loveService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private ShopUserInfoService shopUserInfoService;

    @Autowired
    private GoodsSayService goodsSayService;

    @Autowired
    private ExceptionUtil exceptionUtil;
    @Autowired
    private MailUtil mailUtil;

//    @RequestMapping("shopList")
//    public List<ShopGoods> index(Model model){
//        List<ShopGoods> list = shopGoodsService.selectAllGoods();
//        model.addAttribute("shopList",list);
//        return list;
//    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("pageShopList")
    @ResponseBody
    public PageInfo<ShopGoods> getGoodsList(Integer userId,int pageNum, int pageSize,ShopGoods shopGoods){
        logger.debug("ShopGoodsController:shopGoods"+shopGoods);
        if(shopGoods != null){
            //去首尾空格
            if(shopGoods.getGoodsName() != null){
                shopGoods.setGoodsName(shopGoods.getGoodsName().trim());
            }
            logger.debug("ShopGoodsController:goodsName:"+shopGoods.getGoodsName());
            if("all".equals(shopGoods.getGoodsName())){
                shopGoods.setGoodsName(null);
            }
        }
        //类型为 -100 代表全部类型，置空 不参与条件查询
        if(shopGoods.getGoodsTypeId() != null){
            if(shopGoods.getGoodsTypeId() == -100){
                shopGoods.setGoodsTypeId(null);
            }
        }
        //价格为 0 ，置空 不参与条件查询
        if(shopGoods.getGoodsPrice() != null){
            if(shopGoods.getGoodsPrice() == 0){
                shopGoods.setGoodsPrice(null);
            }
        }
        //成色为 11 ，置空 不参与条件查询
        if(shopGoods.getGoodsQuality() != null){
            if(shopGoods.getGoodsQuality() == 11){
                shopGoods.setGoodsQuality(null);
            }
        }
        return shopGoodsService.getPageGoodsList(userId,pageNum,pageSize,shopGoods);
    }

    @PostMapping("shopList")
    @ResponseBody
    public List<ShopGoods> getShopList(){
        logger.debug("ShopGoodsController:shopList");
        //获取love表goodsId排序
        //根据goodsId查询goods并填充list返回
        List<Love> loveList = loveService.selectGoodsListByLoves();
        List<ShopGoods> shopGoodsList = new ArrayList<>();
        for (Love l: loveList
             ) {
            ShopGoods shopGoods = new ShopGoods();
            shopGoods = shopGoodsService.selectByPrimaryKey(l.getGoodsId());
            shopGoods.setAllLoveNum(l.getNum());
            shopGoodsList.add(shopGoods);
        }
        return shopGoodsList;
    }

    @PostMapping("shopLoveList")
    @ResponseBody
    public List<ShopGoods> getShopLoveList(String userId){
        if("".equals(userId)  || userId == null){
            return null;
        }
        logger.debug("ShopGoodsController:shopLoveList");
        //根据用户id获取love表goodsId
        //根据goodsId查询goods并填充list返回
        List<Love> loveList = loveService.selectByUserId(Integer.parseInt(userId));
        List<ShopGoods> shopGoodsList = new ArrayList<>();
        for (Love l: loveList
        ) {
            ShopGoods shopGoods = new ShopGoods();
            shopGoods = shopGoodsService.selectByPrimaryKey(l.getGoodsId());
            shopGoods.setAllLoveNum(loveService.selectCountByGoodsId(l.getGoodsId()));
            shopGoodsList.add(shopGoods);
        }
        return shopGoodsList;
    }

    @RequestMapping("goodsInfo")
    public String getGoodsInfo(int goodsId, Model model){
        ShopGoods shopGoods = shopGoodsService.selectByPrimaryKey(goodsId);
        if(shopGoods == null)
            return null;
        /**
         * 补全商品及其用户信息并模糊化敏感信息
         */
        UserGoods userGoods = userGoodsService.selectByGoodsId(goodsId);
        //转换时间类型
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userGoods.setAddTimeStr(sf.format(userGoods.getAddTime()));
        shopGoods.setUserGoods(userGoods);
//        ShopUser shopUser = shopUserService.selectById(shopGoods.getUserGoods().getUserId());
//        shopUser.setPwd("*****");
//        shopUser.setPerms("*****");
//        shopGoods.setShopUser(shopUser);
//        ShopUserInfo shopUserInfo = shopUserInfoService.selectById(shopGoods.getShopUser().getId());
//        shopUserInfo.setBirthday(null);
//        shopUserInfo.setIdCard("*****");
//        shopUserInfo.setRealName("*****");
//        shopUserInfo.setXy("*****");
//        shopUserInfo.setProfession("*****");
//        shopUserInfo.setCode("*****");
//        shopUserInfo.setUserClass("*****");
//        shopGoods.setShopUserInfo(shopUserInfo);
        model.addAttribute("goodsInfo",shopGoods);
        model.addAttribute("say",goodsSayService.selectByGoodsId(goodsId));
        return "market/page/product_info";
    }

    /**
     * 商品评论
     * @return
     */
    @PostMapping("saying")
    public String addSaying(GoodsSay s,Model model){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s.setT(sf.format(new Date()));
        try {
            if(goodsSayService.insertSelective(s) == 1){
                model.addAttribute("result","评论成功！");
                //查询用户是否开启邮件提醒，选择发送邮件
                //邮件发送较慢，开一个线程
                new Thread(){
                    public void run(){
                        try {
                            isSayEmail2User(s,1);
                        } catch (Exception e) {
                            exceptionUtil.putException2Table(e);
                        }
                    }
                }.start();
                return "/market/success";
            }else{
                model.addAttribute("result","评论失败！");
                return "/market/error";
            }
        }catch (Exception e){
            model.addAttribute("result","哟，出现了异常。----"+e.getMessage());
            logger.error(e.getMessage());
            return "/market/error";
        }
    }

    public void isSayEmail2User(GoodsSay s,int status){
        UserGoods userGoods = userGoodsService.selectByGoodsId(s.getGid());
        if(userGoods != null) {
            ShopUser shopUser = shopUserService.selectById(userGoods.getUserId());
            if(shopUser != null) {
                int isEmail = shopUser.getLoveMsg();
                if (isEmail == 1) {
                    String recipeEmail = shopUser.getEmail();
                    if (recipeEmail != null && recipeEmail.indexOf("@") != -1) {
                        ShopGoods shopGoods = shopGoodsService.selectByPrimaryKey(s.getGid());
                        String contentStr = s.getContent();
                        if (status == 0) {
                            //用于以后删除评论时的评论语
                        }
                        String content = "【评论提醒】您好，您的商品[" + shopGoods.getGoodsName() + "]刚刚有了新评论： "+ contentStr;
                        mailUtil.sendText(recipeEmail, "【校园二手市场】", content);
                    }
                }
            }
        }
    }
}
