package com.mitail.weixin.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author test
 * @since 2021-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DcmKey implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * key标识
     */
    private String key;

    /**
     * key对应的值
     */
    private String value;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 是否默认 1为默认 2为用户添加
     */
    private Integer sys;


}
