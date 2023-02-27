package com.al.almall.serive.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.al.almall.entity.DTO.AddUserDTO;
import com.al.almall.entity.DTO.RemoveUserDTO;
import com.al.almall.entity.DTO.UpdateUserDTO;
import com.al.almall.entity.DTO.UserListDTO;
import com.al.almall.entity.MallUser;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.UserListVO;
import com.al.almall.exception.RequestException;
import com.al.almall.mapper.MallUserMapper;
import com.al.almall.serive.MallUserService;
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
 * @since 2023-02-26
 */
@Service
public class MallUserServiceImpl extends ServiceImpl<MallUserMapper, MallUser> implements MallUserService {

    private final String DEFAULT_AVATAR = "default_avatar.jpg";
    private final String DEFAULT_PASSWORD = "123456";

    @Autowired
    private MallUserMapper mallUserMapper;

    @Override
    public Result userList(UserListDTO userListDTO) {
        Page<MallUser> userPage = new Page<>(userListDTO.getPageNum(), userListDTO.getPageSize());
        QueryWrapper<MallUser> wrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(userListDTO.getKeyword())) {
            wrapper.like("username", userListDTO.getKeyword());
        }
        Page<MallUser> mallUserPage = mallUserMapper.selectPage(userPage, wrapper);
        UserListVO userListVO = new UserListVO();
        userListVO.setTotal(mallUserPage.getTotal());
        userListVO.setList(mallUserPage.getRecords());
        return Result.success(userListVO);
    }

    @Override
    public Result addUser(AddUserDTO addUserDTO) {
        String username = addUserDTO.getUsername();
        List<MallUser> mallUsers = mallUserMapper.selectList(new LambdaQueryWrapper<MallUser>()
                .eq(MallUser::getUsername, username));
        if (mallUsers.size() > 0) {
            throw new RequestException("当前用户名已存在，请更换后重试");
        }
        MallUser mallUser = new MallUser();
        mallUser.setUsername(username);
        mallUser.setAvatar(DEFAULT_AVATAR);
        mallUser.setName(addUserDTO.getName());
        mallUser.setPhone(addUserDTO.getPhone());
        mallUser.setPassword(DigestUtil.md5Hex(DEFAULT_PASSWORD));
        mallUserMapper.insert(mallUser);
        return Result.success();
    }

    @Override
    public Result removeUser(RemoveUserDTO removeUserDTO) {
        this.removeByIds(removeUserDTO.getList());
        return Result.success();
    }

    @Override
    public Result updateUser(UpdateUserDTO updateUserDTO) {
        mallUserMapper.update(null, new LambdaUpdateWrapper<MallUser>()
        .eq(MallUser::getId, updateUserDTO.getId())
        .set(MallUser::getName, updateUserDTO.getName())
        .set(MallUser::getPhone, updateUserDTO.getPhone()));
        return Result.success();
    }

    @Override
    public Result getUserDetail(Integer id) {
        MallUser mallUser = this.getById(id);
        return Result.success(mallUser);
    }
}
