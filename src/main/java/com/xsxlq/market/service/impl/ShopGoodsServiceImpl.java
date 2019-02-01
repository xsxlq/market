package com.xsxlq.market.service.impl;

import com.xsxlq.market.dao.ShopGoodsMapper;
import com.xsxlq.market.dao.ShopGoodsTypeMapper;
import com.xsxlq.market.dao.ShopQualityMapper;
import com.xsxlq.market.pojo.ShopGoods;
import com.xsxlq.market.pojo.ShopGoodsType;
import com.xsxlq.market.pojo.ShopQuality;
import com.xsxlq.market.service.ShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("ShopGoodsService")
public class ShopGoodsServiceImpl implements ShopGoodsService {
    @Autowired
    private ShopGoodsMapper shopGoodsMapper;
    @Autowired
    private ShopGoodsTypeMapper shopGoodsTypeMapper;
    @Autowired
    private ShopQualityMapper shopQualityMapper;
    /**
     * 查询全部商品
     * @return
     */
    @Override
    public List<ShopGoods> selectAllGoods() {
        List<ShopGoods> list = new ArrayList<>();
        List<ShopGoods> beforeList = shopGoodsMapper.selectAllGoods();
        if(beforeList == null)
            return null;
        for (ShopGoods sg:beforeList
             ) {
            int goodsTypeId = sg.getGoodstypeid();
            int goodsQualityId = sg.getGoodsquality();
            ShopGoodsType sgt = shopGoodsTypeMapper.selectByPrimaryKey(goodsTypeId);
            ShopQuality sq = shopQualityMapper.selectByPrimaryKey(goodsQualityId);
            sg.setShopGoodsType(sgt);
            sg.setShopQuality(sq);
            list.add(sg);
        }
        list = beforeList;
        return list;
    }
    @Override
    public int deleteByPrimaryKey(Integer goodsid) {
        return 0;
    }

    @Override
    public int insert(ShopGoods record) {
        return 0;
    }

    @Override
    public int insertSelective(ShopGoods record) {
        return 0;
    }

    @Override
    public ShopGoods selectByPrimaryKey(Integer goodsid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ShopGoods record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ShopGoods record) {
        return 0;
    }
}
