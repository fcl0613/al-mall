package com.al.almall.service;

import com.al.almall.entity.DTO.ChangeStatusDTO;
import com.al.almall.entity.DTO.ChangeStockDTO;
import com.al.almall.entity.DTO.GoodsListDTO;
import com.al.almall.entity.MallGoods;
import com.al.almall.entity.Result;

public interface GoodsService {
    Result addGoods(MallGoods mallGoods);

    Result goodsList(GoodsListDTO goodsListDTO);

    Result updateGoodsPublish(ChangeStatusDTO changeStatusDTO);

    Result updateStock(ChangeStockDTO changeStockDTO);

    Result removeGoods(Integer id);

    Result getGoodsDetail(Integer id);

    Result updateGoods(MallGoods mallGoods);
}
