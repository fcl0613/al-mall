package com.al.almall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailGoodsDO {
    private String goodsName;
    private String goodsPic;
    private BigDecimal goodsPrice;
    private Integer goodsCount;
    private BigDecimal subtotal;
}
