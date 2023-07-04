package com.al.almall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderListDO {
    private Integer id;
    private String storeName;
    private String orderStatus;
    private BigDecimal totalPrice;
    private LocalDateTime createTime;
}
