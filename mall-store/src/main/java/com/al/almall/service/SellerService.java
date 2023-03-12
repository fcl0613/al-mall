package com.al.almall.service;

import com.al.almall.entity.MallStoreOwner;
import com.al.almall.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SellerService extends IService<MallStoreOwner> {
    Result login(String username, String password);
}
