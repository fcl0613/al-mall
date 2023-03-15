package com.al.almall.dao;

import com.al.almall.entity.DO.OrderDO;
import com.al.almall.entity.DO.OrderDetailDO;
import com.al.almall.entity.DO.OrderDetailGoodsDO;
import com.al.almall.entity.DTO.OrderListDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreOrderDao {
    Page<OrderDO> getOrderList(@Param("page") Page<OrderDO> page,
                               @Param("dto") OrderListDTO getOrderListDTO,
                               @Param("map") Map<Integer, String> map,
                               @Param("storeId") Integer storeId);
    OrderDetailDO getOrderDetail(@Param("orderId") Integer orderId,
                                 @Param("map") Map<Integer, String> map);
    List<OrderDetailGoodsDO> getOrderDetailGoods(@Param("orderId") Integer orderId);

    Integer getYesterdayOrder(@Param("time") String time, @Param("storeId") Integer storeId);
    Integer getYesterdayEffectiveOrderCount(@Param("time") String time, @Param("storeId") Integer storeId);
    String getTotalIncome(@Param("storeId") Integer storeId);
    String getYesterdayIncome(@Param("time") String time, @Param("storeId") Integer storeId);
}
