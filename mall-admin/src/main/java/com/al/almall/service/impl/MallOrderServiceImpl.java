package com.al.almall.service.impl;

import com.al.almall.dao.MallOrderDao;
import com.al.almall.entity.DO.OrderDetailDO;
import com.al.almall.entity.DO.OrderDetailGoodsDO;
import com.al.almall.entity.DO.OrderDo;
import com.al.almall.entity.DTO.GetOrderListDTO;
import com.al.almall.entity.MallOrder;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.GetOrderListVO;
import com.al.almall.entity.VO.OrderDetailVO;
import com.al.almall.enums.OrderStatusEnum;
import com.al.almall.mapper.MallOrderMapper;
import com.al.almall.service.MallOrderService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MallOrderServiceImpl extends ServiceImpl<MallOrderMapper, MallOrder> implements MallOrderService {

    @Autowired
    private MallOrderDao mallOrderDao;

    @Override
    public Result getOrderList(GetOrderListDTO getOrderListDTO) {
        Page<OrderDo> orderDoPage = new Page<>(getOrderListDTO.getPageNum(),
                getOrderListDTO.getPageSize());
        Map<Integer, String> orderStatusMap = OrderStatusEnum.getOrderStatusMap();
        Page<OrderDo> orderList = mallOrderDao.getOrderList(orderDoPage, getOrderListDTO, orderStatusMap);
        GetOrderListVO getOrderListVO = new GetOrderListVO();
        getOrderListVO.setTotal(orderList.getTotal());
        getOrderListVO.setList(orderList.getRecords());
        return Result.success(getOrderListVO);
    }

    @Override
    public Result getOrderDetail(Integer id) {
        // 订单基本信息
        // 订单编号 用户账号 订单金额 订单状态 可获得的积分
        Map<Integer, String> orderStatusMap = OrderStatusEnum.getOrderStatusMap();
        OrderDetailDO orderDetail = mallOrderDao.getOrderDetail(id, orderStatusMap);
        // 商品详情 商品图片 商品名称 价格 数量小计 合计
        List<OrderDetailGoodsDO> orderDetailGoods = mallOrderDao.getOrderDetailGoods(id);
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setOrderDetailDO(orderDetail);
        orderDetailVO.setList(orderDetailGoods);
        return Result.success(orderDetailVO);
    }
}
