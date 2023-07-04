package com.al.almall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    void updatePoints(@Param("offset") Integer points,
                 @Param("userId") Integer userId);
}
