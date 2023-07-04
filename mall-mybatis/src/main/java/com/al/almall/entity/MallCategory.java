package com.al.almall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author al
 * @since 2023-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MallCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String categoryName;

    private String categoryIcon;

    private Integer storeId;

    /**
     * 分类标志0默认分类及店铺不能删除1为每个店铺自定义的分类最多三个
     */
    private Integer flag;


}
