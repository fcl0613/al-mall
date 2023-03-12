package com.al.almall.service;

import com.al.almall.entity.MallOrder;
import com.al.almall.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author al
 * @since 2023-03-11
 */
public interface OrderService extends IService<MallOrder> {

    Result addOrder(Integer storeId, HttpServletRequest request);

    Result getOrderList(HttpServletRequest request);
}
