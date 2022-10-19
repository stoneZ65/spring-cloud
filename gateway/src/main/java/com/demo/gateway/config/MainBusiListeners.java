package com.demo.gateway.config;

import com.demo.gateway.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @description:
 * @author: zhanglei
 * @date: 2021/8/2 14:49
 **/
public class MainBusiListeners implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContextUtils.setContext(contextRefreshedEvent.getApplicationContext());
    }
}
