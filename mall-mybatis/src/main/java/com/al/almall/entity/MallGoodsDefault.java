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
 * @since 2023-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MallGoodsDefault implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String goodsName;

    private Integer categoryId;

    private String goodsPic;

    private String description;

    /**
     * 0下架1上架默认下架
     */
    private Integer goodsStatus;

    private BigDecimal goodsPrice;

}
