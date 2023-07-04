package com.al.almall.service;

import com.al.almall.entity.DTO.LoginDTO;
import com.al.almall.entity.DTO.MallUserUpdateDTO;
import com.al.almall.entity.MallUser;
import com.al.almall.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<MallUser> {
    Result login(LoginDTO loginDTO);
    Result getIndexInfo(HttpServletRequest request);

    Result getMeInfo(HttpServletRequest request);

    Result getSettingInfo(HttpServletRequest request);

    Result updateInfo(MallUserUpdateDTO mallUserUpdateDTO, HttpServletRequest request);
}
