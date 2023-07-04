package com.al.almall.controller;

import com.al.almall.entity.DTO.ChangeStatusDTO;
import com.al.almall.entity.DTO.ChangeStockDTO;
import com.al.almall.entity.DTO.GoodsListDTO;
import com.al.almall.entity.MallGoods;
import com.al.almall.entity.Result;
import com.al.almall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/add")
    public Result addGoods(@RequestBody MallGoods mallGoods) {
        return goodsService.addGoods(mallGoods);
    }

    @PostMapping("/list")
    public Result getGoodsList(@RequestBody GoodsListDTO goodsListDTO) {
        return goodsService.goodsList(goodsListDTO);
    }

    @PostMapping("/publish")
    public Result updateGoodsPublish(@RequestBody ChangeStatusDTO changeStatusDTO) {
        return goodsService.updateGoodsPublish(changeStatusDTO);
    }

    @PostMapping("/stock")
    public Result updateStock(@RequestBody ChangeStockDTO changeStockDTO) {
        return goodsService.updateStock(changeStockDTO);
    }

    @PostMapping("/remove/{id}")
    public Result removeGoods(@PathVariable Integer id) {
        return goodsService.removeGoods(id);
    }

    @GetMapping("/detail/{id}")
    public Result getGoodsDetail(@PathVariable Integer id) {
        return goodsService.getGoodsDetail(id);
    }

    @PostMapping("/update")
    public Result updateGoods(@RequestBody MallGoods mallGoods) {
        return goodsService.updateGoods(mallGoods);
    }

}
