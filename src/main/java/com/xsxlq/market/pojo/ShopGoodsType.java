package com.xsxlq.market.pojo;

import lombok.Data;

/**
 * 商品类型表
 */
@Data
public class ShopGoodsType {
    private Integer goodstypeid;

    private String goodstypename;

    private Integer parentid;
}