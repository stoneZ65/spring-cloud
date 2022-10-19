package com.demo.gateway.designPattern.command.validatePlugin;

import com.demo.gateway.utils.ApplicationContextUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @description: 具体执行器，把需要执行的规则添加到 validatePlugins 中
 * @author: zhanglei
 * @date: 2021/7/31 17:15
 **/
@Component("testValidatePlugin")
public class TestValidatePlugin extends ValidatePluginExecute implements ApplicationContextAware, InitializingBean {

    protected ApplicationContext applicationContext;

    private List<ValidatePlugin> validatePlugins;

    @Override
    protected List<ValidatePlugin> getValidatePlugins() {
        return validatePlugins;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //添加规则
        validatePlugins = Lists.newArrayList();
        validatePlugins.add((ValidatePlugin) this.applicationContext.getBean("validatePluginOne"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        TestValidatePlugin testValidatePlugin = (TestValidatePlugin) ApplicationContextUtils.getBean("testValidatePlugin");
        testValidatePlugin.execute();
    }
}
