package com.al.almall.service;

import com.al.almall.entity.DTO.GetOrderListDTO;
import com.al.almall.entity.MallOrder;
import com.al.almall.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

public interface MallOrderService extends IService<MallOrder> {
    Result getOrderList(GetOrderListDTO getOrderListDTO);

    Result getOrderDetail(Integer id);
}
