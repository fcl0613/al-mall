package com.al.almall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsListDO {
    private Integer id;
    private String goodsName;
    private String goodsPic;
    private Integer stock;
    private String description;
    private BigDecimal goodsPrice;
    private Integer categoryId;
}
