package com.al.almall.dao;

import com.al.almall.entity.DO.CartBaseInfoDO;
import com.al.almall.entity.DO.CartListDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartDao {
    List<CartListDO> getCartList(@Param("userId") Integer userId,
                                 @Param("storeId") Integer storeId);
    CartBaseInfoDO getCartBaseInfo(@Param("userId") Integer userId,
                                   @Param("storeId") Integer storeId);
}
