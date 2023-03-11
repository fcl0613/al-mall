package com.al.almall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartListDO {
    private Integer id;
    private Integer goodsId;
    private String goodsPic;
    private String goodsName;
    private BigDecimal goodsPrice;
    private Integer goodsCount;
}
