package com.al.almall.service.impl;

import com.al.almall.entity.MallCategory;
import com.al.almall.entity.Result;
import com.al.almall.enums.CategoryFlagEnum;
import com.al.almall.mapper.MallCategoryMapper;
import com.al.almall.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private MallCategoryMapper mallCategoryMapper;

    @Override
    public Result getAllDefaultCategory() {
        List<MallCategory> mallCategories = mallCategoryMapper
                .selectList(new LambdaQueryWrapper<MallCategory>()
                        .eq(MallCategory::getFlag, CategoryFlagEnum.DEFAULT.getType()));
        return Result.success(mallCategories);
    }
}
