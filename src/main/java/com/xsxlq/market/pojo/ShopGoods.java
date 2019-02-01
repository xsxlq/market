package com.xsxlq.market.pojo;

import lombok.Data;

/**
 * 商品表
 */
@Data
public class ShopGoods {
    private Integer goodsid;

    private String goodsname;

    private Double goodsprice;

    private Integer goodstypeid;

    private String goodsimg;

    private String goodsdesc;

    private Integer goodsexchange;

    private Integer goodsintegral;

    private String goodsdetail;

    private String goodsintroduct;

    private Integer goodsquality;

    //做拓展存储使用
    private ShopGoodsType shopGoodsType;
    private ShopQuality shopQuality;
}