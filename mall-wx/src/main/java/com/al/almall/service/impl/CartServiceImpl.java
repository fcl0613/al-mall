package com.al.almall.service.impl;

import com.al.almall.dao.CartDao;
import com.al.almall.entity.DO.CartListDO;
import com.al.almall.entity.DTO.AddCartDTO;
import com.al.almall.entity.DTO.GetCartListDTO;
import com.al.almall.entity.MallCart;
import com.al.almall.entity.MallGoods;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.CartListVO;
import com.al.almall.mapper.MallCartMapper;
import com.al.almall.mapper.MallGoodsMapper;
import com.al.almall.service.CartService;
import com.al.almall.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author al
 * @since 2023-03-11
 */
@Service
public class CartServiceImpl extends ServiceImpl<MallCartMapper, MallCart> implements CartService {

    @Autowired
    private MallCartMapper mallCartMapper;

    @Autowired
    private CartDao cartDao;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    @Override
    public Result addCart(AddCartDTO addCartDTO, HttpServletRequest request) {
        MallGoods goods = mallGoodsMapper.selectById(addCartDTO.getGoodsId());
        if (goods.getStock() - 1 < 0) {
            return Result.failed("库存不足");
        }
        Integer userId = getUserId(request);
        MallCart mallCart = new MallCart();
        mallCart.setUserId(userId);
        mallCart.setStoreId(goods.getStoreId());
        mallCart.setGoodsId(addCartDTO.getGoodsId());
        mallCart.setGoodsCount(1);
        mallCart.setGoodsPrice(goods.getGoodsPrice());
        mallCartMapper.insert(mallCart);
        return Result.success();
    }

    @Override
    public Result removeCart(Integer id) {
        mallCartMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result getCartList(GetCartListDTO getCartListDTO, HttpServletRequest request) {
        Integer userId = getUserId(request);
        List<CartListDO> cartList = cartDao.getCartList(userId, getCartListDTO.getStoreId());
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartListDO cartListDO : cartList) {
            totalPrice = totalPrice.add(cartListDO.getGoodsPrice()
                    .multiply(new BigDecimal(cartListDO.getGoodsCount())));
        }
        CartListVO cartListVO = new CartListVO();
        cartListVO.setTotalPrice(totalPrice);
        cartListVO.setList(cartList);
        return Result.success(cartListVO);
    }

    @Override
    public Result plusCart(Integer id) {
        MallCart mallCart = mallCartMapper.selectById(id);
        MallGoods goods = mallGoodsMapper.selectById(mallCart.getGoodsId());
        if (mallCart.getGoodsCount() + 1 > goods.getStock()) {
            return Result.failed("库存不足");
        }
        mallCartMapper.update(null, new LambdaUpdateWrapper<MallCart>()
        .eq(MallCart::getId, id)
        .set(MallCart::getGoodsCount, mallCart.getGoodsCount() + 1));
        return Result.success();
    }

    @Override
    public Result minusCart(Integer id) {
        MallCart mallCart = mallCartMapper.selectById(id);
        mallCartMapper.update(null, new LambdaUpdateWrapper<MallCart>()
                .eq(MallCart::getId, id)
                .set(MallCart::getGoodsCount, mallCart.getGoodsCount() - 1));
        return Result.success();
    }

    @Override
    public Result plusCartForMenu(Integer goodsId, HttpServletRequest request) {
        Integer userId = getUserId(request);
        List<MallCart> mallCarts = mallCartMapper.selectList(new LambdaQueryWrapper<MallCart>()
                .eq(MallCart::getUserId, userId)
                .eq(MallCart::getGoodsId, goodsId));
        MallCart mallCart = mallCarts.get(0);
        MallGoods goods = mallGoodsMapper.selectById(goodsId);
        if (mallCart.getGoodsCount() + 1 > goods.getStock()) {
            return Result.failed("库存不足");
        }
        mallCartMapper.update(null, new LambdaUpdateWrapper<MallCart>()
                .eq(MallCart::getId, mallCart.getId())
                .set(MallCart::getGoodsCount, mallCart.getGoodsCount() + 1));
        return Result.success();
    }

    @Override
    public Result minusCartForMenu(Integer goodsId, HttpServletRequest request) {
        Integer userId = getUserId(request);
        List<MallCart> mallCarts = mallCartMapper.selectList(new LambdaQueryWrapper<MallCart>()
                .eq(MallCart::getUserId, userId)
                .eq(MallCart::getGoodsId, goodsId));
        MallCart mallCart = mallCarts.get(0);
        if (mallCart.getGoodsCount() - 1 == 0) {
            mallCartMapper.delete(new LambdaUpdateWrapper<MallCart>()
            .eq(MallCart::getUserId, userId)
            .eq(MallCart::getGoodsId, goodsId));
            return Result.success();
        }
        mallCartMapper.update(null, new LambdaUpdateWrapper<MallCart>()
                .eq(MallCart::getId, mallCart.getId())
                .set(MallCart::getGoodsCount, mallCart.getGoodsCount() - 1));
        return Result.success();
    }

    private Integer getUserId(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return jwtUtil.getUserId(authorization);
    }
}
