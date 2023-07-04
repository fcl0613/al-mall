package com.al.almall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailDO {
    private Integer id;
    private BigDecimal totalPrice;
    private String orderStatus;
    private Integer points;
    private String username;
}
