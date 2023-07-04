package com.al.almall.service;

import com.al.almall.entity.DTO.GetStoreListDTO;
import com.al.almall.entity.DTO.SearchStoreListDTO;
import com.al.almall.entity.Result;

import javax.servlet.http.HttpServletRequest;

public interface StoreService {
    Result getStoreList(GetStoreListDTO getStoreListDTO);

    Result searchStoreList(SearchStoreListDTO searchStoreListDTO);

    Result getMenuList(Integer id, HttpServletRequest request);

    Result getCartBaseInfo(Integer id, HttpServletRequest request);
}
