package com.al.almall.entity.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderListVO {
    private Integer orderId;
    private String storeName;
    private List<String> goodsPic;
    private String createTime;
    private Integer totalCount;
    private BigDecimal totalPrice;
    private String orderStatus;
}
