package com.al.almall.controller;


import com.al.almall.entity.DTO.ChangeStatusDTO;
import com.al.almall.entity.DTO.GetGoodsDefaultListDTO;
import com.al.almall.entity.MallGoodsDefault;
import com.al.almall.entity.Result;
import com.al.almall.service.MallGoodsDefaultService;
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
 * @author al
 * @since 2023-03-07
 */
@RestController
@RequestMapping("/goods/default")
public class MallGoodsDefaultController {
    @Autowired
    private MallGoodsDefaultService mallGoodsDefaultService;

    @GetMapping("/list")
    public Result getGoodsDefaultList(GetGoodsDefaultListDTO getGoodsDefaultListDTO) {
        return mallGoodsDefaultService.getGoodsList(getGoodsDefaultListDTO);
    }

    @PostMapping("/add")
    public Result addGoods(@RequestBody MallGoodsDefault mallGoods) {
        return mallGoodsDefaultService.addGoods(mallGoods);
    }

    @PostMapping("/update")
    public Result updateGoods(@RequestBody MallGoodsDefault mallGoods) {
        return mallGoodsDefaultService.updateGoods(mallGoods);
    }

    @PostMapping("/remove/{id}")
    public Result removeGoods(@PathVariable Integer id) {
        return mallGoodsDefaultService.removeGoods(id);
    }

    @GetMapping("/get/{id}")
    public Result getGoodsDetail(@PathVariable Integer id) {
        return mallGoodsDefaultService.getGoodsDetail(id);
    }

    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        return mallGoodsDefaultService.changeStatus(changeStatusDTO);
    }

}

