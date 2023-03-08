package com.al.almall.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.al.almall.entity.MallAdmin;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.LoginVo;
import com.al.almall.exception.RequestException;
import com.al.almall.mapper.MallAdminMapper;
import com.al.almall.service.MallAdminService;
import com.al.almall.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author al
 * @since 2023-02-19
 */
@Service
@Slf4j
public class MallAdminServiceImpl extends ServiceImpl<MallAdminMapper, MallAdmin> implements MallAdminService {

    @Resource
    private MallAdminMapper mallAdminMapper;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public Result login(String username, String password) {
        List<MallAdmin> mallAdmins = mallAdminMapper.selectList(new LambdaQueryWrapper<MallAdmin>()
                .eq(MallAdmin::getUsername, username));
        if (mallAdmins.isEmpty()) {
            throw new RequestException("用户名不存在");
        }
        if (mallAdmins.size() > 1) {
            throw new RequestException("用户存在多个请联系运维人员排查");
        }
        MallAdmin mallAdmin = mallAdmins.get(0);
        String passwordDB = mallAdmin.getPassword();
        if (!passwordDB.equals(DigestUtil.md5Hex(password))) {
            throw new RequestException("密码错误");
        }
        String token = jwtUtil.createToken(mallAdmin.getId(), mallAdmin.getUsername());
        log.info("用户名{}生成的token为{}", username,token);
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return Result.success(loginVo);
    }
}
