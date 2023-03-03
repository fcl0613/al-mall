package com.al.almall.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminRoleEnum {
    SUPER_ADMIN(0, "super_admin"),
    STORE(1, "shop_owner");


    private Integer type;
    private String message;
}
