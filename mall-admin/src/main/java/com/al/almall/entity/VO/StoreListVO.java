package com.al.almall.entity.VO;

import com.al.almall.entity.MallStore;
import lombok.Data;

import java.util.List;

@Data
public class StoreListVO {
    private Long total;
    private List<MallStore> list;
}
