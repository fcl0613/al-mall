package com.al.almall.controller;


import com.al.almall.entity.Result;
import com.al.almall.serive.MallAdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author al
 * @since 2023-02-19
 */
@RestController
@RequestMapping("/admin")
public class MallAdminController {

    @Resource
    private MallAdminService mallAdminService;

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
       return mallAdminService.login(username, password);
    }

    @GetMapping("/test")
    public Result Test() {
        return Result.success("测试shiro整合");
    }
}

