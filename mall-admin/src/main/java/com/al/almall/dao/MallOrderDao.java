package com.al.almall.dao;

import com.al.almall.entity.DO.OrderDetailDO;
import com.al.almall.entity.DO.OrderDetailGoodsDO;
import com.al.almall.entity.DO.OrderDo;
import com.al.almall.entity.DTO.GetOrderListDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MallOrderDao {
    Page<OrderDo> getOrderList(@Param("page") Page<OrderDo> page,
                               @Param("dto") GetOrderListDTO getOrderListDTO,
                               @Param("map") Map<Integer, String> map);
    OrderDetailDO getOrderDetail(@Param("orderId") Integer orderId,
                                 @Param("map") Map<Integer, String> map);
    List<OrderDetailGoodsDO> getOrderDetailGoods(@Param("orderId") Integer orderId);

    Integer getYesterdayOrder(@Param("time") String time);
    Integer getYesterdayEffectiveOrderCount(@Param("time") String time);
    String getTotalIncome();
    String getYesterdayIncome(@Param("time") String time);
}
