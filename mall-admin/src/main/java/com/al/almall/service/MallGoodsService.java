package com.al.almall.service;

import com.al.almall.entity.DTO.GetGoodsListDTO;
import com.al.almall.entity.MallGoods;
import com.al.almall.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author al
 * @since 2023-03-07
 */
public interface MallGoodsService extends IService<MallGoods> {

    Result getGoodsList(GetGoodsListDTO getGoodsListDTO);

    Result addGoods(MallGoods mallGoods);

    Result updateGoods(MallGoods mallGoods);

    Result removeGoods(Integer id);

    Result getGoodsDetail(Integer id);
}
