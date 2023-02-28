package com.al.almall.controller;

import com.al.almall.entity.DTO.LoginDTO;
import com.al.almall.entity.DTO.MallUserUpdateDTO;
import com.al.almall.entity.Result;
import com.al.almall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    /**
     * 获取首页展示的个人信息
     * @param request
     * @return
     */
    @GetMapping("/indexInfo")
    public Result getIndexInfo(HttpServletRequest request) {
        return userService.getIndexInfo(request);
    }

    /**
     * 获取我的页面展示的个人信息
     * @param request
     * @return
     */
    @GetMapping("/meInfo")
    public Result getMeInfo(HttpServletRequest request) {
        return userService.getMeInfo(request);
    }

    @GetMapping("/settingInfo")
    public Result getSettingInfo(HttpServletRequest request) {
        return userService.getSettingInfo(request);
    }

    @PostMapping("/update")
    public Result update(@RequestBody MallUserUpdateDTO mallUserUpdateDTO, HttpServletRequest request) {
        return userService.updateInfo(mallUserUpdateDTO, request);
    }

    @GetMapping("/test")
    public Result test() {
        return Result.success("ssdsd");
    }
}
