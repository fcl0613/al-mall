package com.al.almall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryFlagEnum {
    DEFAULT(0, "系统默认分类"),
    CUSTOM(1, "店家新创分类");

    private Integer type;
    private String description;
}
