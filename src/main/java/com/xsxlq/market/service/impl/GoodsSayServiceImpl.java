package com.xsxlq.market.service.impl;

import com.xsxlq.market.dao.GoodsSayMapper;
import com.xsxlq.market.pojo.GoodsSay;
import com.xsxlq.market.service.GoodsSayService;
import com.xsxlq.market.service.ShopUserInfoService;
import com.xsxlq.market.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("GoodsSayService")
public class GoodsSayServiceImpl implements GoodsSayService {
    @Autowired
    private GoodsSayMapper goodsSayMapper;
    @Autowired
    private ShopUserService shopUserService;
    @Autowired
    private ShopUserInfoService shopUserInfoService;

    @Override
    public List<GoodsSay> selectByGoodsId(int gid) {
        List<GoodsSay> sayList = goodsSayMapper.selectByGoodsId(gid);
        List<GoodsSay> listInfo = new ArrayList<>();
        if(sayList != null){
            for (GoodsSay say:sayList
                 ) {
                say.setName(shopUserService.selectById(say.getUid()).getName());
                say.setImg(shopUserInfoService.selectById(say.getUid()).getIcon());
                listInfo.add(say);
            }
        }
        return listInfo;
    }

    @Override
    public int insertSelective(GoodsSay say) {
        return goodsSayMapper.insertSelective(say);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goodsSayMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<GoodsSay> selectALLSelective(GoodsSay gs) {
        return goodsSayMapper.selectALLSelective(gs);
    }
}
