package com.al.almall.controller;


import com.al.almall.entity.DTO.*;
import com.al.almall.entity.Result;
import com.al.almall.serive.MallStoreOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author al
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/owner")
public class MallStoreOwnerController {
    @Autowired
    private MallStoreOwnerService mallStoreOwnerService;

    @GetMapping("/list")
    public Result getOwnerList(OwnerListDTO ownerListDTO) {
        return mallStoreOwnerService.ownerList(ownerListDTO);
    }

    @PostMapping("/add")
    public Result addOwner(@RequestBody AddOwnerDTO addOwnerDTO) {
        return mallStoreOwnerService.addOwner(addOwnerDTO);
    }

    @PostMapping("/remove/{id}")
    public Result removeOwner(@PathVariable Integer id) {
        return mallStoreOwnerService.removeOwner(id);
    }

    @PostMapping("/update")
    public Result updateOwner(@RequestBody UpdateOwnerDTO updateOwnerDTO) {
        return mallStoreOwnerService.updateOwner(updateOwnerDTO);
    }

    @PostMapping("/resetPassword/{id}")
    public Result resetPassword(@PathVariable Integer id) {
        return mallStoreOwnerService.resetPassword(id);
    }

    @PostMapping("/assign")
    public Result assignStore(@RequestBody AssignStoreDTO assignStoreDTO) {
        return mallStoreOwnerService.assignStore(assignStoreDTO);
    }

    @GetMapping("/get/{id}")
    public Result getOwnerDetail(@PathVariable Integer id) {
        return mallStoreOwnerService.getOwnerDetail(id);
    }

    /**
     * 获取当前店主的店家信息
     * @param id
     * @return
     */
    @GetMapping("/getStore/{id}")
    public Result getStore(@PathVariable Integer id) {
        return mallStoreOwnerService.getStore(id);
    }
}

