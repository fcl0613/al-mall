package com.al.almall.entity.VO;

import com.al.almall.entity.MallGoodsDefault;
import lombok.Data;

import java.util.List;

@Data
public class DefaultGoodsListVO {
    private Long total;
    private List<MallGoodsDefault> list;
}
