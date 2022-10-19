package com.demo.user.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.user.model.User;
import com.demo.user.request.UserQueryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/19 11:19
 **/
@Mapper
@Component("UserMapper")
public interface UserMapper extends BaseMapper<User> {

    IPage<User> page(IPage<User> page, @Param("dto") UserQueryDto dto);
}
