package com.demo.baseserver.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @description: rabbitAdmin 的配置。 用于生产者没有创建队列，消费者监听报错的问题,//实现BeanPostProcessor类，使用Bean的生命周期函数
 * @author: zhanglei
 * @date: 2022/8/4 15:07
 **/
@Component
public class DirectRabbitAdminConfig implements BeanPostProcessor {

    //这是创建交换机和队列用的rabbitAdmin对象
    @Resource
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private DirectRabbitConfig directRabbitConfig;



    //初始化rabbitAdmin对象
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    //实例化bean后，也就是Bean的后置处理器
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {


        //死信列表
        rabbitAdmin.declareExchange(directRabbitConfig.deadLetterDirectExchange());
        rabbitAdmin.declareQueue(directRabbitConfig.deadLetterQueueA());

        // 延时
        rabbitAdmin.declareExchange(directRabbitConfig.delayExchange());
        rabbitAdmin.declareQueue(directRabbitConfig.delayQueueA());
        rabbitAdmin.declareQueue(directRabbitConfig.delayQueueB());

        rabbitAdmin.declareExchange(directRabbitConfig.delayExchangeC());
        rabbitAdmin.declareQueue(directRabbitConfig.delayQueueC());


        //创建交换机 创建队列
        rabbitAdmin.declareExchange(directRabbitConfig.rabbitmqDemoDirectExchange());
        rabbitAdmin.declareQueue(directRabbitConfig.rabbitmqDemoDirectQueue());



        //启动项目即创建交换机和队列
        rabbitAdmin.declareExchange(directRabbitConfig.rabbitmqDemoFanoutExchange());
        rabbitAdmin.declareQueue(directRabbitConfig.fanoutExchangeQueueB());
        rabbitAdmin.declareQueue(directRabbitConfig.fanoutExchangeQueueA());


        // 匹配 交换机
        rabbitAdmin.declareExchange(directRabbitConfig.rabbitmqDemoTopicExchange());
        rabbitAdmin.declareQueue(directRabbitConfig.topicExchangeQueueA());
        rabbitAdmin.declareQueue(directRabbitConfig.topicExchangeQueueB());
        rabbitAdmin.declareQueue(directRabbitConfig.topicExchangeQueueC());


        //
        rabbitAdmin.declareExchange(directRabbitConfig.rabbitmqDemoHeadersExchange());
        rabbitAdmin.declareQueue(directRabbitConfig.headersQueueA());
        rabbitAdmin.declareQueue(directRabbitConfig.headersQueueB());


        return null;
    }

}
