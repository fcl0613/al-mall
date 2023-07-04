package com.al.almall.entity.VO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuListGoodsVO {
    private Integer id;
    private String goodsName;
    private String goodsPic;
    private String goodsDescription;
    private BigDecimal goodsPrice;
    private Integer goodsStock;
    private Integer goodsCount;
}
