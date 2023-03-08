package com.al.almall.controller;


import com.al.almall.entity.DTO.AddUserDTO;
import com.al.almall.entity.DTO.RemoveUserDTO;
import com.al.almall.entity.DTO.UpdateUserDTO;
import com.al.almall.entity.DTO.UserListDTO;
import com.al.almall.entity.Result;
import com.al.almall.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author al
 * @since 2023-02-26
 */
@RestController
@RequestMapping("/user")
public class MallUserController {
    @Autowired
    private MallUserService mallUserService;

    @GetMapping("/list")
    public Result userList(UserListDTO userListDTO) {
        return mallUserService.userList(userListDTO);
    }

    @PostMapping("/add")
    public Result userAdd(@RequestBody AddUserDTO addUserDTO) {
        return mallUserService.addUser(addUserDTO);
    }

    @PostMapping("/update")
    public Result userUpdate(@RequestBody UpdateUserDTO updateUserDTO) {
        return mallUserService.updateUser(updateUserDTO);
    }

    @PostMapping("/remove")
    public Result userRemove(@RequestBody RemoveUserDTO removeUserDTO) {
        return mallUserService.removeUser(removeUserDTO);
    }

    @GetMapping("/get/{id}")
    public Result userDetailGet(@PathVariable Integer id) {
        return mallUserService.getUserDetail(id);
    }
}

