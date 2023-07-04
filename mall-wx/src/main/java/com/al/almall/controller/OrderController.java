package com.al.almall.controller;


import com.al.almall.entity.Result;
import com.al.almall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author al
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add/{storeId}")
    public Result addOrder(@PathVariable Integer storeId, HttpServletRequest request) {
        return orderService.addOrder(storeId, request);
    }

    @PostMapping("/list")
    public Result getOrderList(HttpServletRequest request) {
        return orderService.getOrderList(request);
    }
}

