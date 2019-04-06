package com.xsxlq.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xsxlq.market.dao.ShopGoodsMapper;
import com.xsxlq.market.dao.ShopGoodsTypeMapper;
import com.xsxlq.market.dao.ShopQualityMapper;
import com.xsxlq.market.pojo.Love;
import com.xsxlq.market.pojo.ShopGoods;
import com.xsxlq.market.pojo.ShopGoodsType;
import com.xsxlq.market.pojo.ShopQuality;
import com.xsxlq.market.service.LoveService;
import com.xsxlq.market.service.ShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品信息实现类
 */
@Component("ShopGoodsService")
public class ShopGoodsServiceImpl implements ShopGoodsService {
    @Autowired
    private ShopGoodsMapper shopGoodsMapper;
    @Autowired
    private ShopGoodsTypeMapper shopGoodsTypeMapper;
    @Autowired
    private ShopQualityMapper shopQualityMapper;
    @Autowired
    private LoveService loveService;
    /**
     * 查询全部商品
     * @return
     */
    @Override
    public List<ShopGoods> selectAllGoods(Integer userId) {
       return initGoods(userId,shopGoodsMapper.selectAllGoods());
    }

    /**
     * 数据返回前将关联的表数据封装进来
     * @param beforeList
     * @return
     */
    public List<ShopGoods> initGoods(Integer userId,List<ShopGoods> beforeList){
        List<ShopGoods> list = new ArrayList<>();
        if(beforeList == null)
            return null;
        for (ShopGoods sg:beforeList
        ) {
            if(userId == null){
                sg.setIsLove(false);
            }else{
                Love love = new Love();
                love.setUserId(userId);
                love.setGoodsId(sg.getGoodsId());
                sg.setIsLove(loveService.selectByUserIdAndGoodsId(love) == 1);
            }
            //查询被收藏数并赋值
            sg.setAllLoveNum(loveService.selectCountByGoodsId(sg.getGoodsId()));
            int goodsTypeId = sg.getGoodsTypeId();
            int goodsQualityId = sg.getGoodsQuality();
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
    public List<ShopGoods> selectiveGoods(Integer userId,ShopGoods shopGoods) {
        return initGoods(userId,shopGoodsMapper.selectiveGoods(shopGoods));
    }

    /**
     * 商品分页(加入入参来判断用户登录后商品收藏关系)
     * @param
     * @return
     */
    public PageInfo<ShopGoods> getPageGoodsList(Integer userId,int pageNum,int pageSize,ShopGoods shopGoods){
        //使用分页插件
        PageHelper.startPage(pageNum,pageSize);
        List<ShopGoods> pageGoodsList = null;
        if(shopGoods == null){
//            System.out.println("ShopGoodsServiceImpl:查询全部");
            pageGoodsList = selectAllGoods(userId);
        }else{
//            System.out.println("ShopGoodsServiceImpl:条件查询");
            pageGoodsList = selectiveGoods(userId,shopGoods);
        }
        PageInfo<ShopGoods> pageInfo = new PageInfo<>(pageGoodsList);
        return pageInfo;
    }

    @Override
    public int deleteByPrimaryKey(Integer goodsid) {
        return shopGoodsMapper.deleteByPrimaryKey(goodsid);
    }

    /**
     * 添加返回主键
     * @param record
     * @return
     */
    @Override
    @Transactional
    public int insertSelectiveReturnPK(ShopGoods record) {
        return shopGoodsMapper.insertSelectiveReturnPK(record);
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
        ShopGoods shopGoods = new ShopGoods();
        shopGoods.setGoodsId(goodsid);
        List<ShopGoods> shopGoodsList = selectiveGoods(null,shopGoods);
        if(shopGoodsList.size() > 0)
            return shopGoodsList.get(0);

        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ShopGoods record) {
        return shopGoodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ShopGoods record) {
        return shopGoodsMapper.updateByPrimaryKeySelective(record);
    }
}
