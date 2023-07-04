package com.al.almall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum StoreStatusEnum {
    OPEN(0,"营业中"),
    CLOSE(1, "歇业中");

    private Integer type;
    private String message;

    public static Map<Integer,String> getStoreStatusMap() {
        HashMap<Integer, String> map = new HashMap<>();
        for (StoreStatusEnum value : StoreStatusEnum.values()) {
            map.put(value.getType(),value.getMessage());
        }
        return map;
    }
}
