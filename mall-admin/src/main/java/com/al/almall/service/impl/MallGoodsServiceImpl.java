package com.al.almall.service.impl;

import com.al.almall.entity.DTO.GetGoodsListDTO;
import com.al.almall.entity.MallGoods;
import com.al.almall.entity.Result;
import com.al.almall.mapper.MallGoodsMapper;
import com.al.almall.service.MallGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author al
 * @since 2023-03-07
 */
@Service
public class MallGoodsServiceImpl extends ServiceImpl<MallGoodsMapper, MallGoods> implements MallGoodsService {

    @Override
    public Result getGoodsList(GetGoodsListDTO getGoodsListDTO) {
        return null;
    }

    @Override
    public Result addGoods(MallGoods mallGoods) {
        return null;
    }

    @Override
    public Result updateGoods(MallGoods mallGoods) {
        return null;
    }

    @Override
    public Result removeGoods(Integer id) {
        return null;
    }

    @Override
    public Result getGoodsDetail(Integer id) {
        return null;
    }
}
