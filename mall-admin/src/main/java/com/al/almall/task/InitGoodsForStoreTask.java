package com.al.almall.task;

import com.al.almall.entity.MallGoods;
import com.al.almall.entity.MallGoodsDefault;
import com.al.almall.enums.GoodsFlagEnum;
import com.al.almall.mapper.MallGoodsDefaultMapper;
import com.al.almall.service.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于新增商铺时为商铺初始化默认商铺的任务
 */
@Component
@EnableAsync
public class InitGoodsForStoreTask {

    @Autowired
    private MallGoodsDefaultMapper mallGoodsDefaultMapper;

    @Autowired
    private MallGoodsService mallGoodsService;

    public void initGoods(Integer storeId) {
        List<MallGoodsDefault> mallGoodsDefaults = mallGoodsDefaultMapper.selectList(null);
        ArrayList<MallGoods> mallGoods = new ArrayList<>();
        for (MallGoodsDefault mallGoodsDefault : mallGoodsDefaults) {
            MallGoods goods = new MallGoods();
            goods.setStoreId(storeId);
            goods.setStock(0);
            goods.setDefaultId(mallGoodsDefault.getId());
            goods.setGoodsStatus(mallGoodsDefault.getGoodsStatus());
            goods.setDescription(mallGoodsDefault.getDescription());
            goods.setGoodsPic(mallGoodsDefault.getGoodsPic());
            goods.setCategoryId(mallGoodsDefault.getCategoryId());
            goods.setGoodsName(mallGoodsDefault.getGoodsName());
            goods.setFlag(GoodsFlagEnum.DEFAULT.getType());
            goods.setGoodsPrice(mallGoodsDefault.getGoodsPrice());
            goods.setGoodsPoints(mallGoodsDefault.getGoodsPoints());
            mallGoods.add(goods);
        }
        mallGoodsService.saveBatch(mallGoods);
    }
}
