package com.demo.gateway.designPattern.command.validatePlugin;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description: 抽象规则执行器
 * @author: zhanglei
 * @date: 2021/7/31 17:11
 **/
public abstract class ValidatePluginExecute {
    protected abstract List<ValidatePlugin> getValidatePlugins();

    public void execute() {
        final List<ValidatePlugin> validatePlugins = getValidatePlugins();
        if (CollectionUtils.isEmpty(validatePlugins)) {
            return;
        }
        for (ValidatePlugin validatePlugin : validatePlugins) {
            validatePlugin.validate();
        }
    }
}
