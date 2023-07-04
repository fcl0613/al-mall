package com.al.almall.service;

import com.al.almall.entity.MallAdmin;
import com.al.almall.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author al
 * @since 2023-02-19
 */
public interface MallAdminService extends IService<MallAdmin> {
    Result login(String username, String password);
}
