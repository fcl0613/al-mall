package com.al.almall.entity.DO;

import lombok.Data;

@Data
public class OrderDetailListDO {
    private Integer orderId;
    private String goodsPic;
    private Integer totalCount;
}
