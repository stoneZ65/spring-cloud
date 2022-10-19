package com.demo.gateway.designPattern.command.validatePlugin;

import org.springframework.stereotype.Component;

/**
 * @description: 具体测试规则
 * @author: zhanglei
 * @date: 2021/7/31 17:14
 **/
@Component("validatePluginOne")
public class ValidatePluginOne extends ValidatePlugin {
    @Override
    public void validate() {
        System.out.println("validatePluginOne 规则校验");
    }
}
