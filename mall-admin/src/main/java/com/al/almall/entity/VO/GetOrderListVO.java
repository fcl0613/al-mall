package com.al.almall.entity.VO;

import com.al.almall.entity.DO.OrderDo;
import lombok.Data;

import java.util.List;

@Data
public class GetOrderListVO {
    private Long total;
    private List<OrderDo> list;
}
