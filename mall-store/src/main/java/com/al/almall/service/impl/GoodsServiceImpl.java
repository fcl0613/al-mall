package com.al.almall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.al.almall.entity.DTO.ChangeStatusDTO;
import com.al.almall.entity.DTO.ChangeStockDTO;
import com.al.almall.entity.DTO.GoodsListDTO;
import com.al.almall.entity.MallGoods;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.GoodsListVO;
import com.al.almall.enums.GoodsFlagEnum;
import com.al.almall.mapper.MallGoodsMapper;
import com.al.almall.service.GoodsService;
import com.al.almall.utils.JwtUtil;
import com.al.almall.utils.SpringContextUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final String HEADER = "Authorization";
    private final Integer MIX_LIMIT = 5;

    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result addGoods(MallGoods mallGoods) {
        Integer storeId = getStoreId();
        // 判断个性商品是否超出上限
        Integer count = mallGoodsMapper.selectCount(new LambdaQueryWrapper<MallGoods>()
                .eq(MallGoods::getStoreId, storeId)
                .eq(MallGoods::getFlag, GoodsFlagEnum.CUSTOM.getType()));
        if (count > MIX_LIMIT) {
            return Result.failed("自定义产品已达上限");
        }
        mallGoods.setStoreId(storeId);
        mallGoods.setFlag(GoodsFlagEnum.CUSTOM.getType());
        mallGoodsMapper.insert(mallGoods);
        return Result.success();
    }

    @Override
    public Result goodsList(GoodsListDTO goodsListDTO) {
        Integer storeId = getStoreId();
        Page<MallGoods> mallGoodsPage = new Page<>(goodsListDTO.getPageNum(), goodsListDTO.getPageSize());
        QueryWrapper<MallGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_id", storeId);
        if (!StrUtil.isBlank(goodsListDTO.getGoodsName())) {
            queryWrapper.like("goods_name", goodsListDTO.getGoodsName());
        }
        Page<MallGoods> goodsPage = mallGoodsMapper.selectPage(mallGoodsPage, queryWrapper);
        GoodsListVO goodsListVO = new GoodsListVO();
        goodsListVO.setTotal(goodsPage.getTotal());
        goodsListVO.setList(goodsPage.getRecords());
        return Result.success(goodsListVO);
    }

    @Override
    public Result updateGoodsPublish(ChangeStatusDTO changeStatusDTO) {
        mallGoodsMapper.update(null, new LambdaUpdateWrapper<MallGoods>()
        .eq(MallGoods::getId, changeStatusDTO.getId())
        .set(MallGoods::getGoodsStatus, changeStatusDTO.getStatus()));
        return Result.success();
    }

    @Override
    public Result updateStock(ChangeStockDTO changeStockDTO) {
        mallGoodsMapper.update(null, new LambdaUpdateWrapper<MallGoods>()
        .eq(MallGoods::getId, changeStockDTO.getId())
        .set(MallGoods::getStock, changeStockDTO.getStock()));
        return Result.success();
    }

    @Override
    public Result removeGoods(Integer id) {
        Integer storeId = getStoreId();
        Integer count = mallGoodsMapper.selectCount(new LambdaQueryWrapper<MallGoods>()
                .eq(MallGoods::getId, id)
                .eq(MallGoods::getStoreId, storeId)
                .eq(MallGoods::getFlag, GoodsFlagEnum.CUSTOM.getType()));
        if (count == 0) {
            return Result.failed("抱歉, 您无权限操作该商品");
        }
        mallGoodsMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result getGoodsDetail(Integer id) {
        MallGoods mallGoodsDefault = mallGoodsMapper.selectById(id);
        return Result.success(mallGoodsDefault);
    }

    @Override
    public Result updateGoods(MallGoods mallGoods) {
        Integer storeId = getStoreId();
        if (mallGoods.getStoreId() != storeId) {
            return Result.failed("抱歉, 您无权修改此商品");
        }
        mallGoodsMapper.updateById(mallGoods);
        return Result.success();
    }

    private Integer getStoreId () {
        String token = SpringContextUtil.getRequest().getHeader(HEADER);
        return jwtUtil.getStoreId(token);
    }
}
