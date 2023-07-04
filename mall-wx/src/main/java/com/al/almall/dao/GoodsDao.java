package com.al.almall.dao;

import com.al.almall.entity.DO.GoodsListDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsDao {
    List<GoodsListDO> getGoodsList(@Param("storeId") Integer storeId);
}
