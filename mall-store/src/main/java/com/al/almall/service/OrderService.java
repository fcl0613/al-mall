package com.al.almall.service;

import com.al.almall.entity.DTO.OrderListDTO;
import com.al.almall.entity.Result;

public interface OrderService {
    Result getOrderList(OrderListDTO orderListDTO);

    Result OrderDetail(Integer id);

    Result orderFinish(Integer id);
}
