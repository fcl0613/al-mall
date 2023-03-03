package com.al.almall;

import com.al.almall.entity.DO.StoreDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MallStoreOwnerRelationDao {
    void assignStore(@Param("ownerId") Integer ownerId,
                     @Param("storeId") Integer storeId);
    void deleteByOwnerId(@Param("id") Integer id);

    StoreDO getStoreDO(@Param("ownerId") Integer ownerId);
}
