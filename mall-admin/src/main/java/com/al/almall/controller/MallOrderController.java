package com.al.almall.controller;

import com.al.almall.entity.DTO.GetOrderListDTO;
import com.al.almall.entity.Result;
import com.al.almall.service.MallOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class MallOrderController {
    @Autowired
    private MallOrderService mallOrderService;

    // 先暂时做一个查询的
    @PostMapping("/list")
    public Result getOrderList(@RequestBody GetOrderListDTO getOrderListDTO) {
        return mallOrderService.getOrderList(getOrderListDTO);
    }

    @GetMapping("/detail/{id}")
    public Result getOrderDetail(@PathVariable Integer id) {
        return mallOrderService.getOrderDetail(id);
    }
}
