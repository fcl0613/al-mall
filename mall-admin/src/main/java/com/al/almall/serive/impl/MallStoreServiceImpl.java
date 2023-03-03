package com.al.almall.serive.impl;

import cn.hutool.core.util.StrUtil;
import com.al.almall.entity.DTO.AddStoreDTO;
import com.al.almall.entity.DTO.ChangeStatusDTO;
import com.al.almall.entity.DTO.RemoveStoreDTO;
import com.al.almall.entity.DTO.StoreListDTO;
import com.al.almall.entity.MallStore;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.StoreListVO;
import com.al.almall.exception.RequestException;
import com.al.almall.mapper.MallStoreMapper;
import com.al.almall.serive.MallStoreService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author al
 * @since 2023-02-28
 */
@Service
public class MallStoreServiceImpl extends ServiceImpl<MallStoreMapper, MallStore> implements MallStoreService {

    @Autowired
    private MallStoreMapper mallStoreMapper;

    @Override
    public Result storeList(StoreListDTO storeListDTO) {
        Page<MallStore> storePage = new Page<>(storeListDTO.getPageNum(), storeListDTO.getPageSize());
        QueryWrapper<MallStore> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(storeListDTO.getKeyword())) {
            queryWrapper.like("store_name", storeListDTO.getKeyword());
        }
        Page<MallStore> page = mallStoreMapper.selectPage(storePage, queryWrapper);
        StoreListVO storeListVO = new StoreListVO();
        storeListVO.setTotal(page.getTotal());
        storeListVO.setList(page.getRecords());
        return Result.success(storeListVO);
    }

    @Override
    public Result addStore(AddStoreDTO addStoreDTO) {
        String storeName = addStoreDTO.getStoreName();
        List<MallStore> mallStores = mallStoreMapper.selectList(new LambdaQueryWrapper<MallStore>()
                .eq(MallStore::getStoreName, storeName));
        if (mallStores.size() > 0) {
            throw new RequestException("当前店铺名已存在，请更换后重试");
        }
        MallStore mallStore = new MallStore();
        mallStore.setStoreName(storeName);
        mallStore.setStoreAddress(addStoreDTO.getStoreAddress());
        mallStore.setOpeningTime(addStoreDTO.getOpeningTime());
        mallStore.setLongitude(addStoreDTO.getLongitude());
        mallStore.setLatitude(addStoreDTO.getLatitude());
        mallStore.setStatus(addStoreDTO.getStatus());
        mallStore.setPhone(addStoreDTO.getPhone());
        this.save(mallStore);
        return Result.success();
    }

    @Override
    public Result removeStore(RemoveStoreDTO removeStoreDTO) {
        mallStoreMapper.deleteBatchIds(removeStoreDTO.getList());
        return Result.success();
    }

    @Override
    public Result updateStore(MallStore mallStore) {
        mallStoreMapper.updateById(mallStore);
        return Result.success();
    }

    @Override
    public Result getStoreDetail(Integer id) {
        MallStore mallStore = mallStoreMapper.selectById(id);
        return Result.success(mallStore);
    }

    @Override
    public Result changeStatus(ChangeStatusDTO changeStatusDTO) {
        mallStoreMapper.update(null, new LambdaUpdateWrapper<MallStore>()
        .eq(MallStore::getId, changeStatusDTO.getId())
        .set(MallStore::getStatus, changeStatusDTO.getStatus()));
        return Result.success();
    }

    @Override
    public Result searchAll(String keyword) {
        List<MallStore> mallStores = mallStoreMapper.selectList(new LambdaQueryWrapper<MallStore>()
                .like(MallStore::getStoreName, keyword));
        return Result.success(mallStores);
    }
}
