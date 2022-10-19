package com.demo.baseserver.utils.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/19 11:30
 **/
@Data
@ApiModel(value = "分页对象", description = "分页对象")
public class PageDto {

    @ApiModelProperty(value = "总数", name = "total")
    private long total = 0;

    @ApiModelProperty(value = "每页显示条数，默认 20", name = "pageSize")
    private long pageSize = 20;

    @ApiModelProperty(value = "当前页", name = "currentPage")
    private long currentPage = 1;

    public PageDto() {
    }

    public PageDto(long currentPage, long pageSize){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
