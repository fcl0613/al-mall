package com.al.almall.entity.VO;

import com.al.almall.entity.DO.OrderDetailDO;
import com.al.almall.entity.DO.OrderDetailGoodsDO;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetailVO {
    private OrderDetailDO orderDetailDO;
    private List<OrderDetailGoodsDO> list;
}
