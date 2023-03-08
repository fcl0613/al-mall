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
public class MallGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String goodsName;

    private Integer categoryId;

    private Integer storeId;

    private String goodsPic;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 标志0为系统默认商品及每个店铺必须销售1为每个店铺可推出自定义商品
     */
    private Integer flag;

    private Integer goodsStatus;

    private Integer defaultId;

    private BigDecimal goodsPrice;
}
