package com.al.almall.controller;

import com.al.almall.entity.DTO.LoginDTO;
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

    @GetMapping("/indexInfo")
    public Result getIndexInfo(HttpServletRequest request) {
        return userService.getIndexInfo(request);
    }

    @GetMapping("/test")
    public Result test() {
        return Result.success("ssdsd");
    }
}
