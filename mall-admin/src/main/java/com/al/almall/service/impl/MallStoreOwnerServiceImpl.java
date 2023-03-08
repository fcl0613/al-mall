package com.al.almall.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.al.almall.MallStoreOwnerRelationDao;
import com.al.almall.entity.DO.StoreDO;
import com.al.almall.entity.DTO.*;
import com.al.almall.entity.MallStoreOwner;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.OwnerListVO;
import com.al.almall.exception.RequestException;
import com.al.almall.mapper.MallStoreOwnerMapper;
import com.al.almall.service.MallStoreOwnerService;
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
 * @since 2023-03-02
 */
@Service
public class MallStoreOwnerServiceImpl extends ServiceImpl<MallStoreOwnerMapper, MallStoreOwner> implements MallStoreOwnerService {

    private final String PASSWORD = "123456";

    @Autowired
    private MallStoreOwnerMapper mallStoreOwnerMapper;

    @Autowired
    private MallStoreOwnerRelationDao mallStoreOwnerRelationDao;

    @Override
    public Result ownerList(OwnerListDTO ownerListDTO) {
        Page<MallStoreOwner> ownerPage = new Page<>(ownerListDTO.getPageNum(), ownerListDTO.getPageSize());
        QueryWrapper<MallStoreOwner> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(ownerListDTO.getKeyword())) {
            queryWrapper.like("username", ownerListDTO.getKeyword());
        }
        Page<MallStoreOwner> page = mallStoreOwnerMapper.selectPage(ownerPage, queryWrapper);
        OwnerListVO ownerListVO = new OwnerListVO();
        ownerListVO.setTotal(page.getTotal());
        ownerListVO.setList(page.getRecords());
        return Result.success(ownerListVO);
    }

    @Override
    public Result addOwner(AddOwnerDTO addOwnerDTO) {
        String username = addOwnerDTO.getUsername();
        List<MallStoreOwner> mallStoreOwners = mallStoreOwnerMapper.selectList(new LambdaQueryWrapper<MallStoreOwner>()
                .eq(MallStoreOwner::getUsername, username));
        if (mallStoreOwners.size() > 0) {
            throw new RequestException("当前账号已存在,请修改后重试");
        }
        MallStoreOwner mallStoreOwner = new MallStoreOwner();
        mallStoreOwner.setUsername(username);
        mallStoreOwner.setPassword(DigestUtil.md5Hex(PASSWORD));
        mallStoreOwner.setName(addOwnerDTO.getName());
        mallStoreOwner.setPhone(addOwnerDTO.getPhone());
        mallStoreOwner.setIdCard(addOwnerDTO.getIdCard());
        mallStoreOwnerMapper.insert(mallStoreOwner);
        return Result.success();
    }

    @Override
    public Result updateOwner(UpdateOwnerDTO updateOwnerDTO) {
        MallStoreOwner mallStoreOwner = new MallStoreOwner();
        mallStoreOwner.setId(updateOwnerDTO.getId());
        mallStoreOwner.setName(updateOwnerDTO.getName());
        mallStoreOwner.setPhone(updateOwnerDTO.getPhone());
        mallStoreOwner.setIdCard(updateOwnerDTO.getIdCard());
        mallStoreOwnerMapper.updateById(mallStoreOwner);
        return Result.success();
    }

    @Override
    public Result resetPassword(Integer id) {
        String password = DigestUtil.md5Hex(PASSWORD);
        mallStoreOwnerMapper.update(null, new LambdaUpdateWrapper<MallStoreOwner>()
        .eq(MallStoreOwner::getId, id)
        .set(MallStoreOwner::getPassword, password));
        return Result.success();
    }

    @Override
    public Result assignStore(AssignStoreDTO assignStoreDTO) {
        // 分配之前先删除关系
        mallStoreOwnerRelationDao.deleteByOwnerId(assignStoreDTO.getOwnerId());
        mallStoreOwnerRelationDao.assignStore(assignStoreDTO.getOwnerId(), assignStoreDTO.getStoreId());
        return Result.success();
    }

    @Override
    public Result getOwnerDetail(Integer id) {
        MallStoreOwner mallStoreOwner = mallStoreOwnerMapper.selectById(id);
        return Result.success(mallStoreOwner);
    }

    @Override
    public Result removeOwner(Integer id) {
        this.removeById(id);
        // 同时删除关系表的对应数据
        mallStoreOwnerRelationDao.deleteByOwnerId(id);
        return Result.success();
    }

    @Override
    public Result getStore(Integer id) {
        StoreDO storeDO = mallStoreOwnerRelationDao.getStoreDO(id);
        return Result.success(storeDO);
    }
}
