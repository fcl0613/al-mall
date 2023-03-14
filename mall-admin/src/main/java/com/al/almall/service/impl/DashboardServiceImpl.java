package com.al.almall.service.impl;

import cn.hutool.core.date.DateUtil;
import com.al.almall.cache.MyCache;
import com.al.almall.dao.MallOrderDao;
import com.al.almall.entity.MallOrder;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.DashboardVO;
import com.al.almall.enums.OrderStatusEnum;
import com.al.almall.mapper.MallOrderMapper;
import com.al.almall.mapper.MallStoreMapper;
import com.al.almall.mapper.MallUserMapper;
import com.al.almall.service.DashboardService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private MallUserMapper mallUserMapper;

    @Autowired
    private MallStoreMapper mallStoreMapper;

    @Autowired
    private MallOrderMapper mallOrderMapper;

    @Autowired
    private MallOrderDao mallOrderDao;

    @Autowired
    private MyCache myCache;

    @Override
    public Result getData() {
        String time = DateUtil.format(DateUtil.yesterday(), "YYYY-MM-dd");
        if (myCache.hasKey(time)) {
            log.info("走了缓存");
            return Result.success(myCache.get(time));
        }else {
            // 用户总数 店铺总数 订单总数 昨日订单数 有效订单总数 昨日有效订单数 总收入 昨日收入
            Integer userCount = mallUserMapper.selectCount(null);
            Integer storeCount = mallStoreMapper.selectCount(null);
            Integer orderCount = mallOrderMapper.selectCount(null);
            Integer yesterdayOrder = mallOrderDao.getYesterdayOrder(time);
            Integer effectiveOrderCount =
                    mallOrderMapper.selectCount(new LambdaQueryWrapper<MallOrder>()
                            .eq(MallOrder::getOrderStatus, OrderStatusEnum.FINISH.getType()));
            Integer yesterdayEffectiveOrderCount = mallOrderDao.getYesterdayEffectiveOrderCount(time);
            String totalIncome = mallOrderDao.getTotalIncome();
            String yesterdayIncome = mallOrderDao.getYesterdayIncome(time);
            DashboardVO dashboardVO = new DashboardVO();
            dashboardVO.setEffectiveOrderCount(effectiveOrderCount);
            dashboardVO.setOrderCount(orderCount);
            dashboardVO.setStoreCount(storeCount);
            dashboardVO.setTotalIncome(totalIncome);
            dashboardVO.setUserCount(userCount);
            dashboardVO.setYesterdayEffectiveOrderCount(yesterdayEffectiveOrderCount);
            dashboardVO.setYesterdayIncome(yesterdayIncome);
            dashboardVO.setYesterdayOrder(yesterdayOrder);
            String beforeYesterday =
                    DateUtil.format(DateUtil.offsetDay(DateUtil.date(), 2), "YYYY-MM-dd");
            if (myCache.hasKey(beforeYesterday)) {
                myCache.remove(beforeYesterday);
            }

            myCache.set(time, dashboardVO);
            return Result.success(dashboardVO);
        }
    }
}
