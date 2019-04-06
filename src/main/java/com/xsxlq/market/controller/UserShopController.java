package com.xsxlq.market.controller;

import com.xsxlq.market.controller.admin.ExceptionController;
import com.xsxlq.market.dao.ShopGoodsTypeMapper;
import com.xsxlq.market.dao.ShopQualityMapper;
import com.xsxlq.market.pojo.Love;
import com.xsxlq.market.pojo.ShopGoods;
import com.xsxlq.market.pojo.ShopUser;
import com.xsxlq.market.pojo.UserGoods;
import com.xsxlq.market.service.*;
import com.xsxlq.market.util.ExceptionUtil;
import com.xsxlq.market.util.FileUtil;
import com.xsxlq.market.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserShopController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ShopGoodsService shopGoodsService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private LoveService loveService;

    @Autowired
    private ShopQualityMapper shopQualityMapper;

    @Autowired
    private ShopGoodsTypeMapper shopGoodsTypeMapper;

    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    private MailUtil mailUtil;

    /**
     * 添加商品(需要修改两张表--添加事务管理)
     * @param goods
     * @return
     */
    @PostMapping("/insertGoodsbyUser")
    public String insertGoods(@RequestParam("file") MultipartFile file, ShopGoods goods, String userId, Model model){
        logger.debug(goods.toString());
        //添加商品表和用户商品表
        if(userId == null || userId.equals("")){
            logger.debug("用户id获取失败！userId:"+userId);
            return null;
        }
        //处理图片上传
        Map<String,String> returnMap = FileUtil.upload(file,FileUtil.SAVEPATH,FileUtil.FILETYPE,FileUtil.FILESIZE);
        if(returnMap.get("respCode").equals("0")){
            model.addAttribute("result",returnMap.get("respDesc"));
            return "market/error";
        }else if(returnMap.get("respCode").equals("1")){
            String sqlImgUrl = "/upload/img/"+returnMap.get("respNewFileName");
            goods.setGoodsImg(sqlImgUrl);
        }
        try {
            if(shopGoodsService.insertSelectiveReturnPK(goods) == 1){
                logger.debug("商品表添加成功");
            }else{
                logger.warn("商品表添加失败");
                model.addAttribute("result","商品（表）入库失败");
                return "market/error";
            }
        }catch (Exception e){
            model.addAttribute("result",e.getMessage());
            return "market/error";
        }
        int goodsId = goods.getGoodsId();

        if(goodsId <= 0){
            //添加失败
            logger.warn("商品添加失败：goodsId:"+goodsId);
        }else{
            int uid = Integer.parseInt(userId);
            logger.debug("商品添加：goodsId:"+goodsId);
            UserGoods userGoods = new UserGoods();
            userGoods.setGoodsId(goodsId);
            userGoods.setUserId(uid);
            Map<String,String> insertReturnMap = userGoodsService.insert(userGoods);
            if(insertReturnMap.get("respCode").equals("1")){
                logger.debug("用户-商品：入表成功");
                model.addAttribute("result","添加成功");
                return "market/success";
            }else{
                logger.debug("用户-商品：入表失败："+insertReturnMap.get("respDesc"));
                model.addAttribute("result","添加失败");
                return "market/error";
            }
        }
        model.addAttribute("result","未知错误，请check!");
        return "market/error";
    }

    @PostMapping("/updateGoodsbyUser")
    public String updateGoodsbyUser(@RequestParam("file") MultipartFile file, ShopGoods goods, String userId, Model model){
        logger.debug(goods.toString());
        //添加商品表和用户商品表
        if(userId == null || userId.equals("")){
            logger.debug("用户id获取失败！userId:"+userId);
            return null;
        }
        //处理图片上传
        if(file.isEmpty() == true){
            logger.info("update:file is null");
        }else{
            Map<String,String> returnMap = FileUtil.upload(file,FileUtil.SAVEPATH,FileUtil.FILETYPE,FileUtil.FILESIZE);
            if(returnMap.get("respCode").equals("0")){
                model.addAttribute("result",returnMap.get("respDesc"));
                return "market/error";
            }else if(returnMap.get("respCode").equals("1")){
                String sqlImgUrl = "/upload/img/"+returnMap.get("respNewFileName");
                goods.setGoodsImg(sqlImgUrl);
            }
        }
        if(shopGoodsService.updateByPrimaryKeySelective(goods) == 1){
            logger.debug("商品表修改成功");
        }else{
            logger.warn("商品表修改失败");
            model.addAttribute("result","商品（表）修改失败");
            return "market/error";
        }
        model.addAttribute("result","修改成功！");
        return "market/success";
    }

    @PostMapping("/selectUserGoods")
    @ResponseBody
    public List<ShopGoods> selectUserGoods(@RequestParam("userId") String userId){
        logger.debug("执行查询用户商品Controller入参："+userId);
        if(userId == null || "".equals(userId)){
            // 入参id错误
            return null;
        }
        //类型转换
        int uid = Integer.parseInt(userId);
        //执行查询用户商品
        List<UserGoods> userGoodsList = userGoodsService.selectByUserId(uid);
        List<ShopGoods> shopGoodsList = new ArrayList<>();
        ShopGoods shopGoods = null;
        for (UserGoods u:userGoodsList
             ) {
            shopGoods = new ShopGoods();
            shopGoods = shopGoodsService.selectByPrimaryKey(u.getGoodsId());
            if(shopGoods != null){
                shopGoodsList.add(shopGoods);
            }
        }
        return shopGoodsList;
    }
    @PostMapping("/deleteUserGoodsById")
    @ResponseBody
    public int deleteUserGoodsById(String userId,String goodsId){
        logger.debug("执行删除用户商品Controller入参："+userId+"&"+goodsId);
        if(userId == null || "".equals(userId) || goodsId == null || "".equals(goodsId)){
            logger.error("执行删除商品时入参错误 userId:"+userId+"goodsId:"+goodsId);
            // 入参id错误
            return 0;
        }
        //类型转换
        int uid = Integer.parseInt(userId);
        int gid = Integer.parseInt(goodsId);
        // 删除用户商品表的一条数据
        if(userGoodsService.delete(uid,gid) == 1)
            //删除商品表（后期可以改变状态，不删除）
            return shopGoodsService.deleteByPrimaryKey(gid);
        return 0;
    }

    @PostMapping("/editUserGoodsById")
    public String editUserGoodsById(String goodsId,Model model){
//        logger.debug("执行修改用户商品Controller入参："+userId+"&"+goodsId);
//        if(userId == null || "".equals(userId) || goodsId == null || "".equals(goodsId)){
//            // 入参id错误
//            //返回登陆页面
//            return "market/page/login_page";
//        }
        //类型转换
        int gid = Integer.parseInt(goodsId);
        model.addAttribute("result",shopGoodsService.selectByPrimaryKey(gid));
        model.addAttribute("qualityList",shopQualityMapper.selectAll());
        model.addAttribute("typeList",shopGoodsTypeMapper.selectAll());
        return "market/page/personal/exit_my_goods";
    }

    /**
     * 判断是否收藏，用于页面展示
     * @param love
     * @return
     */
    @PostMapping("/loveStatus")
    @ResponseBody
    public String loveStatus(Love love){
        if(loveService.selectByUserIdAndGoodsId(love) == 1){
            return "1";
        }
        return "0";
    }

    /**
     * 收藏
     * @param love
     * @return
     */
    @PostMapping("/love")
    @ResponseBody
    public Map<String,String> loveIt(Love love){
        Map<String,String> returnMap = new HashMap<>();
        if(loveService.insert(love) == 1){
            //添加成功
            returnMap.put("respCode","1");
            returnMap.put("respDesc","添加成功！");
            //查询用户是否使用邮件提醒，判断是否发送邮件

            //邮件发送较慢，开一个线程
            new Thread(){
                public void run(){
                    try{
                    isLikeEmail2User(love,1);
                    }catch (Exception e){
                        exceptionUtil.putException2Table(e);
                    }
                }
            }.start();
        }else{
            //添加失败
            returnMap.put("respCode","0");
            returnMap.put("respDesc","添加失败！");
        }
        return returnMap;
    }

    /**
     * 取消收藏
     * @param love
     * @return
     */
    @PostMapping("/disLike")
    @ResponseBody
    public Map<String,String> disLike(Love love){
        Map<String,String> returnMap = new HashMap<>();
        if(loveService.delete(love) == 1){
            //取消收藏操作成功
            returnMap.put("respCode","1");
            returnMap.put("respDesc","取消收藏操作成功！");

                //邮件发送较慢，开一个线程
                new Thread(){
                    public void run(){
                        try{
                            isLikeEmail2User(love,0);
                        }catch (Exception e){
                            exceptionUtil.putException2Table(e);
                        }
                    }
                }.start();

        }else{
            //添加失败
            returnMap.put("respCode","0");
            returnMap.put("respDesc","取消收藏操作失败！");
        }
        return returnMap;
    }

    public void isLikeEmail2User(Love love,int status) throws Exception{
        UserGoods userGoods = userGoodsService.selectByGoodsId(love.getGoodsId());
        if(userGoods != null) {
            ShopUser shopUser = shopUserService.selectById(userGoods.getUserId());
            if(shopUser != null) {
                int isEmail = shopUser.getLoveMsg();
                if (isEmail == 1) {
                    String recipeEmail = shopUser.getEmail();
                    if (recipeEmail == null || recipeEmail.indexOf("@") == -1) {
                    }
                    ShopGoods shopGoods = shopGoodsService.selectByPrimaryKey(love.getGoodsId());
                    String contentStr = "加入";
                    if(status == 0){
                        contentStr = "取消";
                    }
                    String content = "【收藏提醒】您好，您的商品[" + shopGoods.getGoodsName() + "]被用户[***]"+contentStr+"了收藏，当前收藏数：" + loveService.selectCountByGoodsId(love.getGoodsId()) + "。";
                    mailUtil.sendText(recipeEmail, "【校园二手市场】", content);
                }
            }
        }
    }
}
