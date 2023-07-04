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
 * @since 2023-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MallStore implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 门店名字
     */
    private String storeName;

    /**
     * 门店地址
     */
    private String storeAddress;

    /**
     * 营业时间
     */
    private String openingTime;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 状态0营业中1歇业中默认歇业
     */
    private Integer status;

    /**
     * 门店电话
     */
    private String phone;


}
