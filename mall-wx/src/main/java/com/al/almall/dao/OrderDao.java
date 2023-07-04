package com.al.almall.dao;

import com.al.almall.entity.DO.OrderDetailListDO;
import com.al.almall.entity.DO.OrderListDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDao {
    List<OrderListDO> getOrderList(@Param("userId") Integer userId,
                                   @Param("time") String time,
                                   @Param("map") Map<Integer, String> map);

    List<OrderDetailListDO> getOrderDetailList(@Param("list") List<Integer> list);
}
