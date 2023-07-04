package com.al.almall.controller;


import com.al.almall.entity.DTO.AddCartDTO;
import com.al.almall.entity.DTO.GetCartListDTO;
import com.al.almall.entity.Result;
import com.al.almall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author al
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Result addCart(@RequestBody AddCartDTO addCartDTO, HttpServletRequest request) {
       return cartService.addCart(addCartDTO, request);
    }

    @PostMapping("/remove/{id}")
    public Result removeCart(@PathVariable Integer id) {
        return cartService.removeCart(id);
    }

    @PostMapping("/list")
    public Result getCartList(@RequestBody GetCartListDTO getCartListDTO, HttpServletRequest request) {
        return cartService.getCartList(getCartListDTO, request);
    }

    /**
     * 更新数量 加
     * @return
     */
    @PostMapping("/plus/{id}")
    public Result plusCart(@PathVariable Integer id) {
        return cartService.plusCart(id);
    }

    /**
     * 减数量
     * @param id
     * @return
     */
    @PostMapping("/minus/{id}")
    public Result minusCart(@PathVariable Integer id) {
        return cartService.minusCart(id);
    }

    /**
     * 更新数量 加 在菜单页面使用
     * @return
     */
    @PostMapping("/menu/plus/{id}")
    public Result plusCartForMenu(@PathVariable Integer id, HttpServletRequest request) {
        return cartService.plusCartForMenu(id, request);
    }

    /**
     * 减数量 在菜单页面使用
     * @param id
     * @return
     */
    @PostMapping("/menu/minus/{id}")
    public Result minusCartForMenu(@PathVariable Integer id, HttpServletRequest request) {
        return cartService.minusCartForMenu(id, request);
    }
}

