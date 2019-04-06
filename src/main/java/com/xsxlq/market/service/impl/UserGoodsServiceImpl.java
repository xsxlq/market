package com.xsxlq.market.service.impl;

import com.xsxlq.market.dao.UserGoodsMapper;
import com.xsxlq.market.pojo.UserGoods;
import com.xsxlq.market.service.UserGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("UserGoodsService")
public class UserGoodsServiceImpl implements UserGoodsService {

    @Autowired
    private UserGoodsMapper userGoodsMapper;

    /**
     * 添加
     * @param record
     * @return
     */
    @Override
    @Transactional
    public Map<String,String> insert(UserGoods record) {
        Map<String,String> returnMap = new HashMap<>();
        if(userGoodsMapper.insert(record) == 1){
            returnMap.put("respCode","1");
            returnMap.put("respDesc","用户商品表入库成功！");
        }else{
            returnMap.put("respCode","0");
            returnMap.put("respDesc","用户商品表入库失败！");
        }
        return returnMap;
    }

    @Override
    public List<UserGoods> selectByUserId(int id) {
        return userGoodsMapper.selectByUserId(id);
    }

    @Override
    public UserGoods selectByGoodsId(int id) {
        return userGoodsMapper.selectByGoodsId(id);
    }

    @Override
    public int delete(int userId, int goodsId) {
        return userGoodsMapper.delete(userId,goodsId);
    }

    @Override
    public boolean isLoved(int userId, int goodsId) {
        UserGoods userGoods = userGoodsMapper.selectByGoodsId(goodsId);
        if(userGoods == null){
            return false;
        }
        if(userId == userGoods.getUserId()){
            return true;
        }
        return false;
    }

    @Override
    public List<UserGoods> selectAll(UserGoods userGoods) {
        return userGoodsMapper.selectAll(userGoods);
    }
}
