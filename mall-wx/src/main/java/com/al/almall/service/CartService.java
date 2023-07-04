package com.al.almall.service;

import com.al.almall.entity.DTO.AddCartDTO;
import com.al.almall.entity.DTO.GetCartListDTO;
import com.al.almall.entity.MallCart;
import com.al.almall.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author al
 * @since 2023-03-11
 */
public interface CartService extends IService<MallCart> {

    Result addCart(AddCartDTO addCartDTO, HttpServletRequest request);

    Result removeCart(Integer id);

    Result getCartList(GetCartListDTO getCartListDTO, HttpServletRequest request);

    Result plusCart(Integer id);

    Result minusCart(Integer id);

    Result plusCartForMenu(Integer goodsId, HttpServletRequest request);

    Result minusCartForMenu(Integer goodsId, HttpServletRequest request);
}
