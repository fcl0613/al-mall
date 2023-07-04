package com.al.almall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreOwnerDao {
    Integer getStoreIdByStoreOwner(@Param("ownerId") Integer ownerId);
}
