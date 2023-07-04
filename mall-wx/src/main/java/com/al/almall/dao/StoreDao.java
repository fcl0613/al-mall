package com.al.almall.dao;

import com.al.almall.entity.DO.GetStoreListDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreDao {
    List<GetStoreListDO> getStoreListDO(@Param("arr") String[] arr, @Param("map") Map<Integer,String> map);
    List<GetStoreListDO> searchStoreListDO(@Param("keyword") String keyword, @Param("map") Map<Integer,String> map);
}
