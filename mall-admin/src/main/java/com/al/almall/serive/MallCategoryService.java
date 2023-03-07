package com.al.almall.serive;

import com.al.almall.entity.DTO.CategoryListDTO;
import com.al.almall.entity.MallCategory;
import com.al.almall.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author al
 * @since 2023-03-03
 */
public interface MallCategoryService extends IService<MallCategory> {

    Result categoryList(CategoryListDTO categoryListDTO);

    Result addCategory(MallCategory mallCategory);

    Result updateCategory(MallCategory mallCategory);

    Result removeCategory(Integer id);

    Result getCategoryDetail(Integer id);
}
