package com.al.almall.entity.VO;

import com.al.almall.entity.DO.CartListDO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartListVO {
    private BigDecimal totalPrice;
    private List<CartListDO> list;
}
