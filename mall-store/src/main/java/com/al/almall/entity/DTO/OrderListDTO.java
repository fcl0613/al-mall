package com.al.almall.entity.DTO;

import lombok.Data;

@Data
public class OrderListDTO extends PageParamsDTO{
    private Integer orderId;
    private String beginTime;
    private String endTime;
    private Integer orderStatus;
}
