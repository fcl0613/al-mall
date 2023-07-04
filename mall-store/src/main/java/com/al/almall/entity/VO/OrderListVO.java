package com.al.almall.entity.VO;

import com.al.almall.entity.DO.OrderDO;
import lombok.Data;

import java.util.List;

@Data
public class OrderListVO {
    private Long total;
    private List<OrderDO> list;
}
