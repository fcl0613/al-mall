package com.al.almall.entity.VO;

import com.al.almall.entity.MallGoods;
import lombok.Data;

import java.util.List;

@Data
public class GoodsListVO {
    private Long total;
    private List<MallGoods> list;
}
