package com.al.almall.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class MallOrder implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer storeId;

    private BigDecimal totalPrice;

    private Integer userId;

    private LocalDateTime createTime;

    private Integer points;
}
