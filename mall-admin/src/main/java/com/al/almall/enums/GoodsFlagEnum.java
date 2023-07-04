package com.al.almall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GoodsFlagEnum {
    DEFAULT(0, "系统默认商品"),
    CUSTOM(1, "店家新创");

    private Integer type;
    private String description;
}
