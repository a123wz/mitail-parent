package com.mitail.weixin.entity;

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
public class ActPrize implements Serializable {

    private static final long serialVersionUID=1L;

    private String openId;

    private String address;

    private Integer count;

    private String name;

    private LocalDateTime postTime;

    private Integer state;

    private String tel;

    private LocalDateTime time;

    private Integer scoreId;


}
