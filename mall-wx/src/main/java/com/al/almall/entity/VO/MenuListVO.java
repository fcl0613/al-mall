package com.al.almall.entity.VO;

import lombok.Data;

import java.util.List;

@Data
public class MenuListVO {
    private Integer id;
    private String categoryName;
    private String categoryIcon;
    private List<MenuListGoodsVO> goodsList;
}
