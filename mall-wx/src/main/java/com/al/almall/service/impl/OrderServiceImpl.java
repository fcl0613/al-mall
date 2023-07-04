package com.al.almall.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.al.almall.dao.OrderDao;
import com.al.almall.dao.UserDao;
import com.al.almall.entity.DO.OrderDetailListDO;
import com.al.almall.entity.DO.OrderListDO;
import com.al.almall.entity.MallCart;
import com.al.almall.entity.MallGoods;
import com.al.almall.entity.MallOrder;
import com.al.almall.entity.MallOrderDetail;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.OrderListVO;
import com.al.almall.enums.OrderStatusEnum;
import com.al.almall.mapper.MallCartMapper;
import com.al.almall.mapper.MallGoodsMapper;
import com.al.almall.mapper.MallOrderDetailMapper;
import com.al.almall.mapper.MallOrderMapper;
import com.al.almall.mapper.MallUserMapper;
import com.al.almall.service.OrderDetailService;
import com.al.almall.service.OrderService;
import com.al.almall.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author al
 * @since 2023-03-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<MallOrderMapper, MallOrder> implements OrderService {

    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    @Autowired
    private MallCartMapper mallCartMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MallOrderDetailMapper mallOrderDetailMapper;

    @Autowired
    private MallOrderMapper mallOrderMapper;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private MallUserMapper mallUserMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Result addOrder(Integer storeId, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Integer userId = jwtUtil.getUserId(authorization);
        // 获取购物车内的所有商品
        List<MallCart> mallCarts = mallCartMapper.selectList(new LambdaQueryWrapper<MallCart>()
                .eq(MallCart::getUserId, userId)
                .eq(MallCart::getStoreId, storeId));
        ArrayList<Integer> cartGoodsIds = new ArrayList<>();
        HashMap<Integer, Integer> cartMap = new HashMap<>();
        for (MallCart mallCart : mallCarts) {
            cartGoodsIds.add(mallCart.getGoodsId());
            cartMap.put(mallCart.getGoodsId(),mallCart.getGoodsCount());
        }
        List<MallGoods> mallGoods = mallGoodsMapper.selectBatchIds(cartGoodsIds);
        BigDecimal orderPrice = BigDecimal.ZERO;
        Integer points = 0;
        for (MallGoods mallGood : mallGoods) {
            Integer goodsCount = cartMap.get(mallGood.getId());
            if (goodsCount > mallGood.getStock()) {
                return Result.failed(mallGood.getGoodsName() + "库存不足");
            }
            orderPrice = orderPrice.add(mallGood.getGoodsPrice()
                    .multiply(new BigDecimal(goodsCount)));
            points += mallGood.getGoodsPoints();
        }
        // 更新库存
        for (MallGoods mallGood : mallGoods) {
            mallGoodsMapper.update(null, new LambdaUpdateWrapper<MallGoods>()
            .eq(MallGoods::getId, mallGood.getId())
            .set(MallGoods::getStock, mallGood.getStock() - cartMap.get(mallGood.getId())));
        }
        MallOrder mallOrder = new MallOrder();
        mallOrder.setStoreId(storeId);
        mallOrder.setTotalPrice(orderPrice);
        mallOrder.setUserId(userId);
        mallOrder.setPoints(points);
        mallOrder.setOrderStatus(OrderStatusEnum.WAIT.getType());
        // 插入订单表
        mallOrderMapper.insert(mallOrder);
        ArrayList<MallOrderDetail> mallOrderDetails = new ArrayList<>();
        for (MallCart mallCart : mallCarts) {
            MallOrderDetail mallOrderDetail = new MallOrderDetail();
            mallOrderDetail.setOrderId(mallOrder.getId());
            mallOrderDetail.setGoodsId(mallCart.getGoodsId());
            mallOrderDetail.setGoodsCount(mallCart.getGoodsCount());
            mallOrderDetails.add(mallOrderDetail);
        }
        // 插入订单详情表
        orderDetailService.saveBatch(mallOrderDetails);
        // 清空购物车数据
        mallCartMapper.delete(new LambdaUpdateWrapper<MallCart>()
        .eq(MallCart::getStoreId, storeId)
        .eq(MallCart::getUserId, userId));
        // TODO 调用第三方支付接口
        // 更新积分信息 这步到完成订单后在做
//        userDao.updatePoints(points, userId);
        return Result.success();
    }

    @Override
    public Result getOrderList(HttpServletRequest request) {
        // 这里就默认找近半年的订单
        String dateTime = DateUtil.format(DateUtil.offset(DateUtil.date(),
                DateField.DAY_OF_MONTH, -6),
                "yyyy-MM-dd HH:mm:ss");
        String authorization = request.getHeader("Authorization");
        Integer userId = jwtUtil.getUserId(authorization);
        List<OrderListDO> orderList =
                orderDao.getOrderList(userId, dateTime, OrderStatusEnum.getOrderStatusMap());
        List<Integer> orderIds = new ArrayList<>();
        for (OrderListDO orderListDO : orderList) {
            orderIds.add(orderListDO.getId());
        }
        List<OrderDetailListDO> orderDetailList = orderDao.getOrderDetailList(orderIds);
        ArrayList<OrderListVO> orderListVOS = new ArrayList<>();
        for (OrderListDO orderListDO : orderList) {
            for (OrderDetailListDO orderDetailListDO : orderDetailList) {
                if (orderListDO.getId() == orderDetailListDO.getOrderId()) {
                    OrderListVO orderListVO = new OrderListVO();
                    orderListVO.setOrderId(orderListDO.getId());
                    orderListVO.setCreateTime(DateUtil.format(orderListDO.getCreateTime(),
                            "yyyy-MM-dd HH:mm:ss"));
                    String[] split = orderDetailListDO.getGoodsPic().split(",");
                    orderListVO.setGoodsPic(Arrays.asList(split));
                    orderListVO.setStoreName(orderListDO.getStoreName());
                    orderListVO.setTotalCount(orderDetailListDO.getTotalCount());
                    orderListVO.setTotalPrice(orderListDO.getTotalPrice());
                    orderListVO.setOrderStatus(orderListDO.getOrderStatus());
                    orderListVOS.add(orderListVO);
                }
            }
        }
        return Result.success(orderListVOS);
    }
}
