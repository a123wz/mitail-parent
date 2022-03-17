package com.mitail.weixin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class DcmAdmin implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 性别
     */
    private Boolean sex;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 是否有效
     */
    private Integer state;

    /**
     * 添加时间
     */
    private LocalDateTime createTime;

    /**
     * 添加用户id
     */
    private Integer parentId;

    /**
     * 微信绑定的微信id
     */
    private String opendId;

    private String lastLoginIp;

    private LocalDateTime lastLoginTime;


}
