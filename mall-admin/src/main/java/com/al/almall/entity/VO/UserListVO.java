package com.al.almall.entity.VO;

import com.al.almall.entity.MallUser;
import lombok.Data;

import java.util.List;

@Data
public class UserListVO {
    private Long total;
    private List<MallUser> list;
}
