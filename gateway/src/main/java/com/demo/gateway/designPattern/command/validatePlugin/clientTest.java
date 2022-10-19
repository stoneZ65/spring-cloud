package com.demo.gateway.designPattern.command.validatePlugin;


import com.demo.gateway.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: zhanglei
 * @date: 2021/8/2 13:46
 **/

public class clientTest {


    public static void main(String[] args) {
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TestValidatePlugin testValidatePlugin = (TestValidatePlugin) ApplicationContextUtils.getBean("testValidatePlugin");
        testValidatePlugin.execute();
    }

}
