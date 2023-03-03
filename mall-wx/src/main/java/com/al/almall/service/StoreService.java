package com.al.almall.service;

import com.al.almall.entity.DTO.GetStoreListDTO;
import com.al.almall.entity.DTO.SearchStoreListDTO;
import com.al.almall.entity.Result;

public interface StoreService {
    Result getStoreList(GetStoreListDTO getStoreListDTO);

    Result searchStoreList(SearchStoreListDTO searchStoreListDTO);
}
