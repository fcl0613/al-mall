package com.al.almall.serive.impl;

import cn.hutool.core.util.StrUtil;
import com.al.almall.entity.DTO.CategoryListDTO;
import com.al.almall.entity.MallCategory;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.CategoryListVO;
import com.al.almall.mapper.MallCategoryMapper;
import com.al.almall.serive.MallCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author al
 * @since 2023-03-03
 */
@Service
public class MallCategoryServiceImpl extends ServiceImpl<MallCategoryMapper, MallCategory> implements MallCategoryService {

    @Autowired
    private MallCategoryMapper mallCategoryMapper;

    @Override
    public Result categoryList(CategoryListDTO categoryListDTO) {
        Page<MallCategory> categoryPage = new Page<>(categoryListDTO.getPageNum(), categoryListDTO.getPageSize());
        QueryWrapper<MallCategory> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(categoryListDTO.getKeyword())) {
            queryWrapper.like("category_name", categoryListDTO.getKeyword());
        }
        Page<MallCategory> page = mallCategoryMapper.selectPage(categoryPage, queryWrapper);
        CategoryListVO categoryListVO = new CategoryListVO();
        categoryListVO.setTotal(page.getTotal());
        categoryListVO.setList(page.getRecords());
        return Result.success(categoryListVO);
    }

    @Override
    public Result addCategory(MallCategory mallCategory) {
        mallCategory.setStoreId(0);
        mallCategory.setFlag(0);
        this.save(mallCategory);
        return Result.success();
    }

    @Override
    public Result updateCategory(MallCategory mallCategory) {
        this.updateById(mallCategory);
        return Result.success();
    }

    @Override
    public Result removeCategory(Integer id) {
        this.removeById(id);
        return Result.success();
    }

    @Override
    public Result getCategoryDetail(Integer id) {
        MallCategory category = this.getById(id);
        return Result.success(category);
    }
}
