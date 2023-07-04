package com.al.almall.service;

import com.al.almall.entity.DTO.*;
import com.al.almall.entity.MallStore;
import com.al.almall.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author al
 * @since 2023-02-28
 */
public interface MallStoreService extends IService<MallStore> {
    Result storeList(StoreListDTO storeListDTO);
    Result addStore(AddStoreDTO addStoreDTO);
    Result removeStore(RemoveStoreDTO removeStoreDTO);
    Result updateStore(MallStore mallStore);
    Result getStoreDetail(Integer id);

    Result changeStatus(ChangeStatusDTO changeStatusDTO);

    Result searchAll(String keyword);
}
