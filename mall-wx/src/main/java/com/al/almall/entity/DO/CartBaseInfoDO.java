package com.al.almall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartBaseInfoDO {
    private Integer totalCount;
    private BigDecimal totalPrice;
}
