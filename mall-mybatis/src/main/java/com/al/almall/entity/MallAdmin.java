package com.al.almall.entity;

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
 * @since 2023-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MallAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String phone;

    private String idCard;

    /**
     * 头像
     */
    private String avatar;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
