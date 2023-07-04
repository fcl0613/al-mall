package com.al.almall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDO {
    private Integer id;
    private BigDecimal totalPrice;
    private LocalDateTime createTime;
    private String orderStatus;
    private String username;
}
