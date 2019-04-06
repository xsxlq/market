package com.xsxlq.market.service.impl;

import com.xsxlq.market.dao.LoveMapper;
import com.xsxlq.market.pojo.Love;
import com.xsxlq.market.service.LoveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LoveService")
public class LoveServiceImpl implements LoveService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoveMapper loveMapper;

    /**
     * 收藏添加
     * @param record
     * @return
     */
    @Override
    public int insert(Love record) {
        //收藏前判断是否已收藏
        if(selectByUserIdAndGoodsId(record) == 0){
            //未收藏
            return loveMapper.insert(record);
        }else{
            //已收藏
            logger.warn("LoveServiceImpl--insert("+record.getUserId()+","+record.getGoodsId()+")--重复已收藏操作，直接返回1");
            return 1;
        }
    }

    @Override
    public List<Love> selectByUserId(int userId) {
        return loveMapper.selectByUserId(userId);
    }

    @Override
    public List<Love> selectByGoodsId(int goodsId) {
        return loveMapper.selectByGoodsId(goodsId);
    }

    @Override
    public int delete(Love l) {
        return loveMapper.delete(l);
    }

    @Override
    public int selectByUserIdAndGoodsId(Love love) {
        if(loveMapper.selectByUserIdAndGoodsId(love) == null){
            return 0;
        }
        return 1;
    }

    @Override
    public int selectCountByGoodsId(int goodsId) {
        return loveMapper.selectCountByGoodsId(goodsId);
    }

    @Override
    public List<Love> selectGoodsListByLoves() {
        return loveMapper.selectGoodsListByLoves();
    }
}
