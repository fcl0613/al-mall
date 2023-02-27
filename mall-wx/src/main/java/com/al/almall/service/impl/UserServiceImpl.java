package com.al.almall.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.al.almall.entity.DTO.LoginDTO;
import com.al.almall.entity.MallUser;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.IndexInfoVO;
import com.al.almall.entity.VO.LoginVO;
import com.al.almall.exception.RequestException;
import com.al.almall.mapper.MallUserMapper;
import com.al.almall.service.UserService;
import com.al.almall.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<MallUserMapper, MallUser> implements  UserService {

    @Autowired
    private MallUserMapper mallUserMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result login(LoginDTO loginDTO) {
        List<MallUser> mallUsers = mallUserMapper.selectList(new LambdaQueryWrapper<MallUser>()
                .eq(MallUser::getUsername, loginDTO.getUsername()));
        if (mallUsers.size() > 1) {
            throw new RequestException("用户名不唯一，请联系客服");
        }
        if (mallUsers.size() == 0) {
            throw new RequestException("用户不存在");
        }
        MallUser mallUser = mallUsers.get(0);
        if (!mallUser.getPassword().equals(DigestUtil.md5Hex(loginDTO.getPassword()))) {
            throw new RequestException("密码错误");
        }
        String token = jwtUtil.createToken(mallUser.getId(), mallUser.getUsername());
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        return Result.success(loginVO);
    }

    @Override
    public Result getIndexInfo(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Integer userId = jwtUtil.getUserId(authorization);
        MallUser mallUser = mallUserMapper.selectById(userId);
        IndexInfoVO indexInfoVO = new IndexInfoVO();
        indexInfoVO.setUsername(mallUser.getUsername());
        indexInfoVO.setPoints(mallUser.getPoints());
        return Result.success(indexInfoVO);
    }
}
