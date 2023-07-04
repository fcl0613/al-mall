package com.al.almall.service;

import com.al.almall.entity.DTO.ChangeStatusDTO;
import com.al.almall.entity.DTO.GetGoodsDefaultListDTO;
import com.al.almall.entity.MallGoodsDefault;
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
public interface MallGoodsDefaultService extends IService<MallGoodsDefault> {

    Result getGoodsList(GetGoodsDefaultListDTO getGoodsDefaultListDTO);

    Result addGoods(MallGoodsDefault mallGoods);

    Result updateGoods(MallGoodsDefault mallGoods);

    Result removeGoods(Integer id);

    Result getGoodsDetail(Integer id);

    Result changeStatus(ChangeStatusDTO changeStatusDTO);
}
