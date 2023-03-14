package com.al.almall.entity.VO;

import lombok.Data;

@Data
public class DashboardVO {
    private Integer userCount;
    private Integer storeCount;
    private Integer orderCount;
    private Integer yesterdayOrder;
    private Integer effectiveOrderCount;
    private Integer yesterdayEffectiveOrderCount;
    private String totalIncome;
    private String yesterdayIncome;
}
