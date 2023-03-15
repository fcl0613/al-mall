package com.al.almall.service.impl;

import cn.hutool.core.date.DateUtil;
import com.al.almall.cache.MyCache;
import com.al.almall.dao.StoreOrderDao;
import com.al.almall.entity.MallOrder;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.DashboardVO;
import com.al.almall.enums.OrderStatusEnum;
import com.al.almall.mapper.MallOrderMapper;
import com.al.almall.service.DashboardService;
import com.al.almall.utils.JwtUtil;
import com.al.almall.utils.SpringContextUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {
    private final String HEADER = "Authorization";
    private final String GIP = "#";

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyCache myCache;

    @Autowired
    private MallOrderMapper mallOrderMapper;

    @Autowired
    private StoreOrderDao storeOrderDao;

    @Override
    public Result getData() {
        String time = DateUtil.format(DateUtil.yesterday(), "YYYY-MM-dd");
        Integer storeId = getStoreId();
        String key = time + GIP + storeId;
        if (myCache.hasKey(time)) {
            log.info("走了缓存");
            return Result.success(myCache.get(key));
        }else {
            // 订单总数 昨日订单数 有效订单总数 昨日有效订单数 总收入 昨日收入
            Integer orderCount = mallOrderMapper.selectCount(new LambdaQueryWrapper<MallOrder>()
            .eq(MallOrder::getStoreId, storeId));
            Integer yesterdayOrder = storeOrderDao.getYesterdayOrder(time, storeId);
            Integer effectiveOrderCount =
                    mallOrderMapper.selectCount(new LambdaQueryWrapper<MallOrder>()
                            .eq(MallOrder::getOrderStatus, OrderStatusEnum.FINISH.getType()));
            Integer yesterdayEffectiveOrderCount = storeOrderDao.getYesterdayEffectiveOrderCount(time, storeId);
            String totalIncome = storeOrderDao.getTotalIncome(storeId);
            String yesterdayIncome = storeOrderDao.getYesterdayIncome(time, storeId);
            DashboardVO dashboardVO = new DashboardVO();
            dashboardVO.setOrderCount(orderCount);
            dashboardVO.setEffectiveOrderCount(effectiveOrderCount);
            dashboardVO.setTotalIncome(totalIncome);
            dashboardVO.setYesterdayEffectiveOrderCount(yesterdayEffectiveOrderCount);
            dashboardVO.setYesterdayIncome(yesterdayIncome);
            dashboardVO.setYesterdayOrder(yesterdayOrder);
            String beforeYesterday =
                    DateUtil.format(DateUtil.offsetDay(DateUtil.date(), 2), "YYYY-MM-dd");
            String yesterdayKey = beforeYesterday + GIP + storeId;
            if (myCache.hasKey(yesterdayKey)) {
                myCache.remove(yesterdayKey);
            }

            myCache.set(key, dashboardVO);
            return Result.success(dashboardVO);
        }
    }

    private Integer getStoreId() {
        String token = SpringContextUtil.getRequest().getHeader(HEADER);
        return jwtUtil.getStoreId(token);
    }
}
