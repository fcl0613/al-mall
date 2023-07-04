package com.al.almall.controller;


import com.al.almall.entity.DTO.GetGoodsListDTO;
import com.al.almall.entity.MallGoods;
import com.al.almall.entity.Result;
import com.al.almall.service.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * 仅用作测试的那三个接口的具体功能应该在店家那边实现的
 * 我们的需求是每次创建一个店家之后就会把默认需要的商品自动给它加入到goods表中
 * @author al
 * @since 2023-03-07
 */
@RestController
@RequestMapping("/goods")
public class MallGoodsController {
    @Autowired
    private MallGoodsService mallGoodsService;

    @GetMapping("/list")
    public Result getGoodsList(GetGoodsListDTO getGoodsListDTO) {
        return mallGoodsService.getGoodsList(getGoodsListDTO);
    }

    /**
     * 此处仅用作测试
     * @param mallGoods
     * @return
     */
    @PostMapping("/add")
    public Result addGoods(@RequestBody MallGoods mallGoods) {
        return mallGoodsService.addGoods(mallGoods);
    }

    /**
     * 此处仅用作测试
     * @param mallGoods
     * @return
     */
    @PostMapping("/update")
    public Result updateGoods(@RequestBody MallGoods mallGoods) {
        return mallGoodsService.updateGoods(mallGoods);
    }

    /**
     * 此处仅用作测试
     * @param id
     * @return
     */
    @PostMapping("/remove/{id}")
    public Result removeGoods(@PathVariable Integer id) {
        return mallGoodsService.removeGoods(id);
    }

    @GetMapping("/get/{id}")
    public Result getGoodsDetail(@PathVariable Integer id) {
        return mallGoodsService.getGoodsDetail(id);
    }

}

