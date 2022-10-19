package com.demo.user.request;

import com.demo.baseserver.utils.page.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/19 11:28
 **/
@Data
@ApiModel(value = "用户查询对象")
public class UserQueryDto extends PageDto {

    @ApiModelProperty(value = "名称", name = "name")
    private String name;

}
