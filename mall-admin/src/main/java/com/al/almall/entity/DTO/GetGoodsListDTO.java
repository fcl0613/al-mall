package com.al.almall.entity.DTO;

import lombok.Data;

@Data
public class GetGoodsListDTO extends PageParamsDTO{
    private String keyword;
    private Integer flag;
}
