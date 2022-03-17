package com.mitail.weixin.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class ActWeixinUser implements Serializable {

    private static final long serialVersionUID=1L;

    private String openId;

    private String city;

    private String country;

    private String nickName;

    private String photo;

    private String province;

    private Integer sex;

    private LocalDateTime time;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("refreshTime")
    private LocalDateTime refreshTime;

    private Integer type;


}
