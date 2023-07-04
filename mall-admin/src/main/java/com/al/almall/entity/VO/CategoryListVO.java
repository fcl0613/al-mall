package com.al.almall.entity.VO;

import com.al.almall.entity.MallCategory;
import lombok.Data;

import java.util.List;

@Data
public class CategoryListVO {
    private Long total;
    private List<MallCategory> list;
}
