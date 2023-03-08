package com.al.almall.service;

import com.al.almall.entity.DTO.AddUserDTO;
import com.al.almall.entity.DTO.RemoveUserDTO;
import com.al.almall.entity.DTO.UpdateUserDTO;
import com.al.almall.entity.DTO.UserListDTO;
import com.al.almall.entity.MallUser;
import com.al.almall.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author al
 * @since 2023-02-26
 */
public interface MallUserService extends IService<MallUser> {
    Result userList(UserListDTO userListDTO);
    Result addUser(AddUserDTO addUserDTO);
    Result removeUser(RemoveUserDTO removeUserDTO);
    Result updateUser(UpdateUserDTO updateUserDTO);
    Result getUserDetail(Integer id);
}
