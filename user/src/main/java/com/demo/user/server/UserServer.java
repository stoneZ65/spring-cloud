package com.demo.user.server;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.user.model.User;
import com.demo.user.request.UserQueryDto;

/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/19 15:54
 **/
public interface UserServer extends IService<User> {

    IPage<User> page(UserQueryDto dto);
}
