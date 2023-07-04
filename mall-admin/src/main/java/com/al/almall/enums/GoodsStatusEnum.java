package com.al.almall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  GoodsStatusEnum {
    PUT_ON_SHELVES(1, "上架"),
    PULL_OFF_SHELVES(0, "下架");

    private Integer type;
    private String description;
}
