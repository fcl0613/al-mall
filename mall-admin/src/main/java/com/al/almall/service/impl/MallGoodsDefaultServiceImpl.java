package com.al.almall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.al.almall.entity.DTO.ChangeStatusDTO;
import com.al.almall.entity.DTO.GetGoodsDefaultListDTO;
import com.al.almall.entity.MallGoods;
import com.al.almall.entity.MallGoodsDefault;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.DefaultGoodsListVO;
import com.al.almall.mapper.MallGoodsDefaultMapper;
import com.al.almall.mapper.MallGoodsMapper;
import com.al.almall.service.MallGoodsDefaultService;
import com.al.almall.task.AddGoodsForStoreTask;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class MallGoodsDefaultServiceImpl extends ServiceImpl<MallGoodsDefaultMapper, MallGoodsDefault> implements MallGoodsDefaultService {

    @Autowired
    private MallGoodsDefaultMapper mallGoodsDefaultMapper;

    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    @Autowired
    private AddGoodsForStoreTask addGoodsForStoreTask;

    @Override
    public Result getGoodsList(GetGoodsDefaultListDTO dto) {
        Page<MallGoodsDefault> defaultPage =
                new Page<>(dto.getPageNum(), dto.getPageSize());
        QueryWrapper<MallGoodsDefault> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(dto.getKeyword())) {
            queryWrapper.like("goods_name", dto.getKeyword());
        }
        Page<MallGoodsDefault> page = mallGoodsDefaultMapper.selectPage(defaultPage, queryWrapper);
        DefaultGoodsListVO defaultGoodsListVO = new DefaultGoodsListVO();
        defaultGoodsListVO.setTotal(page.getTotal());
        defaultGoodsListVO.setList(page.getRecords());
        return Result.success(defaultGoodsListVO);
    }

    @Override
    public Result addGoods(MallGoodsDefault mallGoods) {
        this.save(mallGoods);
        // 这里找到所有的店家并插入goods表 异步的方式进行
        addGoodsForStoreTask.addGoods(mallGoods);
        return Result.success();
    }

    @Override
    public Result updateGoods(MallGoodsDefault mallGoods) {
        mallGoodsDefaultMapper.updateById(mallGoods);
        // 这里从goods表中找到所有对应默认商品的id进行统一更新
        // 需要改变的参数
        MallGoods goods = new MallGoods();
        goods.setGoodsName(mallGoods.getGoodsName());
        goods.setCategoryId(mallGoods.getCategoryId());
        goods.setGoodsPic(mallGoods.getGoodsPic());
        goods.setDescription(mallGoods.getDescription());
        goods.setGoodsStatus(mallGoods.getGoodsStatus());
        mallGoodsMapper.update(goods,
                new LambdaUpdateWrapper<MallGoods>().eq(MallGoods::getDefaultId, mallGoods.getId()));
        return Result.success();
    }

    @Override
    public Result removeGoods(Integer id) {
        this.removeById(id);
        mallGoodsMapper.delete(new LambdaQueryWrapper<MallGoods>().eq(MallGoods::getDefaultId, id));
        return Result.success();
    }

    @Override
    public Result getGoodsDetail(Integer id) {
        MallGoodsDefault mallGoodsDefault = mallGoodsDefaultMapper.selectById(id);
        return Result.success(mallGoodsDefault);
    }

    @Override
    public Result changeStatus(ChangeStatusDTO changeStatusDTO) {
        mallGoodsDefaultMapper.update(null, new LambdaUpdateWrapper<MallGoodsDefault>()
        .eq(MallGoodsDefault::getId, changeStatusDTO.getId())
        .set(MallGoodsDefault::getGoodsStatus, changeStatusDTO.getStatus()));
        mallGoodsMapper.update(null, new LambdaUpdateWrapper<MallGoods>()
        .eq(MallGoods::getDefaultId, changeStatusDTO.getId())
        .set(MallGoods::getGoodsStatus, changeStatusDTO.getStatus()));
        return Result.success();
    }
}
