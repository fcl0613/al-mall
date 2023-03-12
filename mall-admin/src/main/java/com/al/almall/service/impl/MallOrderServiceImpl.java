package com.al.almall.service.impl;

import com.al.almall.entity.DTO.GetOrderListDTO;
import com.al.almall.entity.MallOrder;
import com.al.almall.entity.Result;
import com.al.almall.mapper.MallOrderMapper;
import com.al.almall.service.MallOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MallOrderServiceImpl extends ServiceImpl<MallOrderMapper, MallOrder> implements MallOrderService {
    @Override
    public Result getOrderList(GetOrderListDTO getOrderListDTO) {
        return null;
    }
}
