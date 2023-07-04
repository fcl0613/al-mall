package com.al.almall.entity.DTO;

import lombok.Data;

@Data
public class UserListDTO extends PageParamsDTO{
    private String keyword;
}
