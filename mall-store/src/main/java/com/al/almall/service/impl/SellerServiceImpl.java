package com.al.almall.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.al.almall.dao.StoreOwnerDao;
import com.al.almall.entity.MallStoreOwner;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.LoginVO;
import com.al.almall.exception.RequestException;
import com.al.almall.mapper.MallStoreOwnerMapper;
import com.al.almall.service.SellerService;
import com.al.almall.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl extends ServiceImpl<MallStoreOwnerMapper, MallStoreOwner> implements SellerService {

    @Autowired
    private MallStoreOwnerMapper mallStoreOwnerMapper;

    @Autowired
    private StoreOwnerDao storeOwnerDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result login(String username, String password) {
        List<MallStoreOwner> mallStoreOwners = mallStoreOwnerMapper.selectList(new LambdaQueryWrapper<MallStoreOwner>()
                .eq(MallStoreOwner::getUsername, username));
        if (mallStoreOwners.size() > 1) {
            throw new RequestException("用户名不唯一，请联系客服");
        }
        if (mallStoreOwners.size() == 0) {
            throw new RequestException("用户不存在");
        }
        MallStoreOwner mallStoreOwner = mallStoreOwners.get(0);
        if (!mallStoreOwner.getPassword().equals(DigestUtil.md5Hex(password))) {
            throw new RequestException("密码错误");
        }
        Integer storeId = storeOwnerDao.getStoreIdByStoreOwner(mallStoreOwner.getId());
        String token = jwtUtil.createToken(mallStoreOwner.getId(), mallStoreOwner.getUsername(), storeId);
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        return Result.success(loginVO);
    }
}
