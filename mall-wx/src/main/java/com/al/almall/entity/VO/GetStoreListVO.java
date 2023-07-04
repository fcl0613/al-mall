package com.al.almall.entity.VO;

import lombok.Data;

@Data
public class GetStoreListVO {
    private Integer id;
    private String storeName;
    private String storeAddress;
    private String openingTime;
    private String longitude;
    private String latitude;
    private String status;
    private String phone;
    private String distance;
}
