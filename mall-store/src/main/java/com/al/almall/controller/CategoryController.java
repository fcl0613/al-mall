package com.al.almall.controller;

import com.al.almall.entity.Result;
import com.al.almall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public Result getAllDefaultCategory() {
        return categoryService.getAllDefaultCategory();
    }
}
