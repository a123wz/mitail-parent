package com.mitail.base;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel("分页响应类")
public class PageRes<T> {
//    @ApiModelProperty("总记录数")
    private Long count;
//    @ApiModelProperty("当前页数")
    private Long page;
//    @ApiModelProperty("总页数")
    private Long pageCount;
//    @ApiModelProperty("每页记录数")
    private Long pageSize;
//    @ApiModelProperty("记录集合")
    private List<T> list;

    /**
     * page 封装
     * @param page
     */
    public PageRes(IPage<T> page) {
        this.count = page.getTotal();
        this.page = page.getCurrent()+1;
        this.pageCount = page.getPages();
        this.pageSize = page.getSize();
        this.list = page.getRecords();
    }

    public void initParam(IPage<T> page){
        this.count = page.getTotal();
        this.page = page.getCurrent()+1;
        this.pageCount = page.getPages();
        this.pageSize = page.getSize();
    }

//    public void setPageInfo(ResPageInfo pageInfo){
//        this.count = pageInfo.getCount();
//        this.page = pageInfo.getPage();
//        this.pageCount = pageInfo.getPageCount();
//        this.pageSize = pageInfo.getPageSize();
//    }
}