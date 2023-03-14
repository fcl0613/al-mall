package com.al.almall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    WAIT(0,"等待中"),
    FINISH(1, "已完成");

    private Integer type;
    private String message;

    public static Map<Integer,String> getOrderStatusMap() {
        HashMap<Integer, String> map = new HashMap<>();
        for (OrderStatusEnum value : OrderStatusEnum.values()) {
            map.put(value.getType(),value.getMessage());
        }
        return map;
    }
}
