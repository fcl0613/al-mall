package com.al.almall.service;

import com.al.almall.entity.DTO.AddOwnerDTO;
import com.al.almall.entity.DTO.AssignStoreDTO;
import com.al.almall.entity.DTO.OwnerListDTO;
import com.al.almall.entity.DTO.UpdateOwnerDTO;
import com.al.almall.entity.MallStoreOwner;
import com.al.almall.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author al
 * @since 2023-03-02
 */
public interface MallStoreOwnerService extends IService<MallStoreOwner> {

    Result ownerList(OwnerListDTO ownerListDTO);

    Result addOwner(AddOwnerDTO addOwnerDTO);

    Result updateOwner(UpdateOwnerDTO updateOwnerDTO);

    Result resetPassword(Integer id);

    Result assignStore(AssignStoreDTO assignStoreDTO);

    Result getOwnerDetail(Integer id);

    Result removeOwner(Integer id);

    Result getStore(Integer id);
}
