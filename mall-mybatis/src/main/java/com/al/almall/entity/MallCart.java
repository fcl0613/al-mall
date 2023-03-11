package com.al.almall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author al
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MallCart implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer storeId;

    private Integer userId;

    private Integer goodsId;

    private Integer goodsCount;

    private BigDecimal goodsPrice;
}
