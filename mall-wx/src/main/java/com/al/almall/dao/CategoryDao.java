package com.al.almall.dao;

import com.al.almall.entity.DO.CategoryListDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryDao {
    List<CategoryListDO> getCategoryList(@Param("storeId") Integer storeId);
}
