package com.al.almall.controller;


import com.al.almall.entity.DTO.CategoryListDTO;
import com.al.almall.entity.MallCategory;
import com.al.almall.entity.Result;
import com.al.almall.serive.MallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author al
 * @since 2023-03-03
 */
@RestController
@RequestMapping("/category")
public class MallCategoryController {
    @Autowired
    private MallCategoryService mallCategoryService;

    @GetMapping("/list")
    public Result getCategoryList(CategoryListDTO categoryListDTO) {
        return mallCategoryService.categoryList(categoryListDTO);
    }

    @PostMapping("/add")
    public Result addCategory(@RequestBody MallCategory mallCategory) {
        return mallCategoryService.addCategory(mallCategory);
    }

    @PostMapping("/update")
    public Result updateCategory(@RequestBody MallCategory mallCategory) {
        return mallCategoryService.updateCategory(mallCategory);
    }

    @PostMapping("/remove/{id}")
    public Result removeCategory(@PathVariable Integer id) {
        return mallCategoryService.removeCategory(id);
    }

    @GetMapping("/get/{id}")
    public Result getCategoryDetail(@PathVariable Integer id) {
        return mallCategoryService.getCategoryDetail(id);
    }
}

