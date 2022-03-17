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
public class DcmModel implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private String key;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否显示 1为显示 2为不显示
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父级模板id
     */
    private String parentId;

    /**
     * 描述
     */
    private String note;

    private String url;


}
