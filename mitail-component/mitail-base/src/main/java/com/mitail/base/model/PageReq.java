package com.mitail.base.model;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageReq<T> {

    /*
    @ApiModelProperty("当前页")
    @Min(value=1,message="当前页必须大于等于1")
    */
    private Integer page;

//    @ApiModelProperty("每页记录条数")
//    @Min(value=1,message="每页条数必须大于等于1")
    private Integer pageSize = 50;

//    @ApiModelProperty("查询对象")
    private T param;
}
