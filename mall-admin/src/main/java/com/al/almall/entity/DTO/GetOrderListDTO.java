package com.al.almall.entity.DTO;

import lombok.Data;

@Data
public class GetOrderListDTO extends PageParamsDTO{
    private Integer orderId;
    private Integer storeId;
    private String time;
    private Integer orderStatus;
}
