package com.al.almall.controller;

import com.al.almall.entity.DTO.OrderListDTO;
import com.al.almall.entity.Result;
import com.al.almall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/list")
    public Result getOrderList(@RequestBody OrderListDTO orderListDTO) {
        return orderService.getOrderList(orderListDTO);
    }

    @GetMapping("/detail/{id}")
    public Result orderDetail(@PathVariable Integer id) {
        return orderService.OrderDetail(id);
    }

    @PostMapping("/finish/{id}")
    public Result orderFinish(@PathVariable Integer id) {
        return orderService.orderFinish(id);
    }


}
