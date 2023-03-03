package com.al.almall.entity.VO;

import com.al.almall.entity.MallStoreOwner;
import lombok.Data;

import java.util.List;

@Data
public class OwnerListVO {
    private Long total;
    private List<MallStoreOwner> list;
}
