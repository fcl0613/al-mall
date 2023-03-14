package com.al.almall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDo {
    private Integer id;
    private BigDecimal totalPrice;
    private LocalDateTime createTime;
    private String orderStatus;
    private String storeName;
    private String username;
}
