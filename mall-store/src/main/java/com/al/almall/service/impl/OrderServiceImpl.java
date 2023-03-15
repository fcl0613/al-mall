package com.al.almall.service.impl;

import com.al.almall.dao.StoreOrderDao;
import com.al.almall.dao.StoreUserDao;
import com.al.almall.entity.DO.OrderDO;
import com.al.almall.entity.DO.OrderDetailDO;
import com.al.almall.entity.DO.OrderDetailGoodsDO;
import com.al.almall.entity.DTO.OrderListDTO;
import com.al.almall.entity.MallOrder;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.OrderDetailVO;
import com.al.almall.entity.VO.OrderListVO;
import com.al.almall.enums.OrderStatusEnum;
import com.al.almall.mapper.MallOrderMapper;
import com.al.almall.service.OrderService;
import com.al.almall.utils.JwtUtil;
import com.al.almall.utils.SpringContextUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private final String HEADER = "Authorization";

    @Autowired
    private MallOrderMapper mallOrderMapper;

    @Autowired
    private StoreOrderDao storeOrderDao;

    @Autowired
    private StoreUserDao storeUserDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result getOrderList(OrderListDTO orderListDTO) {
        Integer storeId = getStoreId();
        Page<OrderDO> orderDOPage = new Page<>(orderListDTO.getPageNum(), orderListDTO.getPageSize());
        Map<Integer, String> orderStatusMap = OrderStatusEnum.getOrderStatusMap();
        Page<OrderDO> orderList = storeOrderDao.getOrderList(orderDOPage, orderListDTO, orderStatusMap, storeId);
        OrderListVO orderListVO = new OrderListVO();
        orderListVO.setTotal(orderList.getTotal());
        orderListVO.setList(orderList.getRecords());
        return Result.success(orderListVO);
    }

    @Override
    public Result OrderDetail(Integer id) {
        Map<Integer, String> orderStatusMap = OrderStatusEnum.getOrderStatusMap();
        OrderDetailDO orderDetail = storeOrderDao.getOrderDetail(id, orderStatusMap);
        // 商品详情 商品图片 商品名称 价格 数量小计 合计
        List<OrderDetailGoodsDO> orderDetailGoods = storeOrderDao.getOrderDetailGoods(id);
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setOrderDetailDO(orderDetail);
        orderDetailVO.setList(orderDetailGoods);
        return Result.success(orderDetailVO);
    }

    @Override
    public Result orderFinish(Integer id) {
        MallOrder mallOrder = mallOrderMapper.selectById(id);
        // 更新状态 同时给用户对应积分
        mallOrderMapper.update(null, new LambdaUpdateWrapper<MallOrder>()
        .eq(MallOrder::getId, id)
        .set(MallOrder::getOrderStatus, OrderStatusEnum.FINISH.getType()));
        storeUserDao.updatePoints(mallOrder.getPoints(), mallOrder.getUserId());
        return Result.success();
    }

    private Integer getStoreId() {
        String token = SpringContextUtil.getRequest().getHeader(HEADER);
        return jwtUtil.getStoreId(token);
    }
}
