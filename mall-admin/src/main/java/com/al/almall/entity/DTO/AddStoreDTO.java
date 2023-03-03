package com.al.almall.entity.DTO;

import lombok.Data;

@Data
public class AddStoreDTO {
    private String storeName;
    private String storeAddress;
    private String openingTime;
    private String longitude;
    private String latitude;
    private Integer status;
    private String phone;
}
