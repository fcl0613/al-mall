package com.al.almall.entity.DO;

import lombok.Data;

@Data
public class GetStoreListDO {
    private Integer id;
    private String storeName;
    private String storeAddress;
    private String openingTime;
    private String longitude;
    private String latitude;
    private String status;
    private String phone;
}
