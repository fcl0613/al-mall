package com.al.almall.task;

import com.al.almall.entity.MallGoods;
import com.al.almall.entity.MallGoodsDefault;
import com.al.almall.entity.MallStore;
import com.al.almall.enums.GoodsFlagEnum;
import com.al.almall.enums.GoodsStatusEnum;
import com.al.almall.mapper.MallStoreMapper;
import com.al.almall.service.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableAsync
public class AddGoodsForStoreTask {

    @Autowired
    private MallStoreMapper mallStoreMapper;

    @Autowired
    private MallGoodsService mallGoodsService;

    @Async
    public void addGoods(MallGoodsDefault mallGoodsDefault) {
        List<MallStore> mallStores = mallStoreMapper.selectList(null);
        ArrayList<MallGoods> mallGoods = new ArrayList<>();

        for (MallStore mallStore : mallStores) {
            MallGoods goods = new MallGoods();
            goods.setStoreId(mallStore.getId());
            goods.setStock(0);
            goods.setDefaultId(mallGoodsDefault.getId());
            goods.setGoodsStatus(mallGoodsDefault.getGoodsStatus());
            goods.setDescription(mallGoodsDefault.getDescription());
            goods.setGoodsPic(mallGoodsDefault.getGoodsPic());
            goods.setCategoryId(mallGoodsDefault.getCategoryId());
            goods.setGoodsName(mallGoodsDefault.getGoodsName());
            goods.setGoodsPrice(mallGoodsDefault.getGoodsPrice());
            goods.setFlag(GoodsFlagEnum.DEFAULT.getType());
            mallGoods.add(goods);
        }
        mallGoodsService.saveBatch(mallGoods);
    }
}
