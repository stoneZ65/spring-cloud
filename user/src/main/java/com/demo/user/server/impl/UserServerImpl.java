package com.demo.user.server.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.user.dao.mapper.UserMapper;
import com.demo.user.model.User;
import com.demo.user.request.UserQueryDto;
import com.demo.user.server.UserServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/19 15:56
 **/
@Slf4j
@Service
public class UserServerImpl extends ServiceImpl<UserMapper, User> implements UserServer {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> page(UserQueryDto dto) {
        Page<User> page = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        return userMapper.page(page, dto);
    }
}
