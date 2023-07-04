package com.al.almall.entity.VO;

import lombok.Data;

@Data
public class DashboardVO {
    private Integer orderCount;
    private Integer yesterdayOrder;
    private Integer effectiveOrderCount;
    private Integer yesterdayEffectiveOrderCount;
    private String totalIncome;
    private String yesterdayIncome;
}
