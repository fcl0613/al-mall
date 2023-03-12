package com.al.almall.entity.DTO;

import lombok.Data;

@Data
public class GetOrderListDTO {
    private Integer orderId;
    private Integer storeId;
    private String startTime;
    private String endTime;
}
