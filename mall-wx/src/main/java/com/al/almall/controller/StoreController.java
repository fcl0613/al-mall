package com.al.almall.controller;

import com.al.almall.entity.DTO.GetStoreListDTO;
import com.al.almall.entity.DTO.SearchStoreListDTO;
import com.al.almall.entity.Result;
import com.al.almall.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping("/list")
    public Result getStoreList(@RequestBody GetStoreListDTO getStoreListDTO) {
        return storeService.getStoreList(getStoreListDTO);
    }

    @PostMapping("/search")
    public Result searchStoreList(@RequestBody SearchStoreListDTO searchStoreListDTO) {
        return storeService.searchStoreList(searchStoreListDTO);
    }

    /**
     * 获取一个店铺下的菜单
     * @param id
     * @return
     */
    @PostMapping("/menuList/{id}")
    public Result getMenuList(@PathVariable Integer id, HttpServletRequest request) {
        return storeService.getMenuList(id ,request);
    }

    /**
     *
     * @param id  店铺编号
     * @param request
     * @return
     */
    @PostMapping("/cartBaseInfo/{id}")
    public Result getCartBaseInfo(@PathVariable Integer id, HttpServletRequest request) {
        return storeService.getCartBaseInfo(id, request);
    }
}
