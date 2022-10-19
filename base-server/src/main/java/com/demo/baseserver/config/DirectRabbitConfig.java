package com.demo.baseserver.config;

import com.demo.baseserver.constant.RabbitMqConfig;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @description: Rabbit 队列，路由声明，绑定规则
 * @author: zhanglei
 * @date: 2022/8/4 15:07
 **/
@Configuration
public class DirectRabbitConfig {

    /**
     * 死信队列
     *
     * @return
     */
    @Bean
    public Queue deadLetterQueueA() {
        return new Queue(RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER, true, false, false);
    }

    /**
     * 死信路由
     */
    @Bean
    public DirectExchange deadLetterDirectExchange() {
        return new DirectExchange(RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER_EXCHANGE, true, false);
    }

    /**
     * 死信队列绑定到死信路由交换机
     *
     * @return
     */
    @Bean
    public Binding bindDeadLetterDirect() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(deadLetterQueueA())
                //到交换机
                .to(deadLetterDirectExchange())
                //并设置匹配键
                .with(RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER_DIRECT_ROUTING);
    }

    /**
     * 声明延时队列A
     * @return
     */
    @Bean
    public Queue delayQueueA() {
        Map<String, Object> args = new HashMap<>(3);
        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER_EXCHANGE);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER_DIRECT_ROUTING);
        // x-message-ttl  声明队列的TTL
        args.put("x-message-ttl", 6000);
        return new Queue(RabbitMqConfig.RABBITMQ_DEMO_DELAY_QUEUE_A, true, false, false, args);
    }

    /**
     * 声明延时队列A
     * @return
     */
    @Bean
    public Queue delayQueueB() {
        Map<String, Object> args = new HashMap<>(3);
        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER_EXCHANGE);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER_DIRECT_ROUTING);
        // x-message-ttl  声明队列的TTL
        args.put("x-message-ttl", 60000);
        return new Queue(RabbitMqConfig.RABBITMQ_DEMO_DELAY_QUEUE_B, true, false, false, args);
    }


    /**
     * 声明延时路由
     * @return
     */
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(RabbitMqConfig.RABBITMQ_DEMO_DELAY_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindDelayDirectA(){
        return BindingBuilder
                .bind(delayQueueA())
                .to(delayExchange())
                .with(RabbitMqConfig.RABBITMQ_DEMO_DELAY_ROUTING_A);
    }

    @Bean
    public Binding bindDelayDirectB(){
        return BindingBuilder
                .bind(delayQueueB())
                .to(delayExchange())
                .with(RabbitMqConfig.RABBITMQ_DEMO_DELAY_ROUTING_B);
    }


    /***/

    /**
     * 声明延时队列A
     * @return
     */
    @Bean
    public Queue delayQueueC() {
        return new Queue(RabbitMqConfig.RABBITMQ_DEMO_DELAY_QUEUE_C, true, false, false);
    }

    @Bean
    public CustomExchange delayExchangeC(){
        Map<String,Object> args = new HashMap<>(1);
        args.put("x-delayed-type","direct");
        return new CustomExchange(RabbitMqConfig.RABBITMQ_DEMO_DELAY_EXCHANGE_C,"x-delayed-message",true,false,args);
    }

    @Bean
    public Binding bindDelayDirectC(){
        return BindingBuilder
                .bind(delayQueueC())
                .to(delayExchangeC())
                .with(RabbitMqConfig.RABBITMQ_DEMO_DELAY_ROUTING_C).noargs();
    }

    @Bean
    public Queue rabbitmqDemoDirectQueue() {
        Map<String, Object> args = new HashMap<>(2);
        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER_EXCHANGE);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER_DIRECT_ROUTING);

        /**
         * 1、name:    队列名称
         * 2、durable: 是否持久化
         * 3、exclusive: 是否独享、排外的。如果设置为true，定义为排他队列。则只有创建者可以使用此队列。也就是private私有的。
         * 4、autoDelete: 是否自动删除。也就是临时队列。当最后一个消费者断开连接后，会自动删除。
         * 5. arguments 私信队列的参数
         * */
        return new Queue(RabbitMqConfig.RabbitMQ_DEMO_TOPIC, true, false, false, args);
    }

    @Bean
    public DirectExchange rabbitmqDemoDirectExchange() {
        //Direct交换机
        return new DirectExchange(RabbitMqConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindDirect() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(rabbitmqDemoDirectQueue())
                //到交换机
                .to(rabbitmqDemoDirectExchange())
                //并设置匹配键
                .with(RabbitMqConfig.RABBITMQ_DEMO_DIRECT_ROUTING);
    }


    /*************Fanout Exchange*************/


    @Bean
    public Queue fanoutExchangeQueueA() {
        //队列A
        return new Queue(RabbitMqConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_A, true, false, false);
    }

    @Bean
    public Queue fanoutExchangeQueueB() {
        //队列B
        return new Queue(RabbitMqConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_B, true, false, false);
    }

    @Bean
    public FanoutExchange rabbitmqDemoFanoutExchange() {
        //创建FanoutExchange类型交换机
        return new FanoutExchange(RabbitMqConfig.FANOUT_EXCHANGE_DEMO_NAME, true, false);
    }

    @Bean
    public Binding bindFanoutA() {
        //队列A绑定到FanoutExchange交换机
        return BindingBuilder.bind(fanoutExchangeQueueA()).to(rabbitmqDemoFanoutExchange());
    }

    @Bean
    public Binding bindFanoutB() {
        //队列B绑定到FanoutExchange交换机
        return BindingBuilder.bind(fanoutExchangeQueueB()).to(rabbitmqDemoFanoutExchange());
    }


    /*************Topic Exchange*************/


    @Bean
    public TopicExchange rabbitmqDemoTopicExchange() {
        //配置TopicExchange交换机
        return new TopicExchange(RabbitMqConfig.TOPIC_EXCHANGE_DEMO_NAME, true, false);
    }

    @Bean
    public Queue topicExchangeQueueA() {
        //队列A
        return new Queue(RabbitMqConfig.TOPIC_EXCHANGE_QUEUE_A, true, false, false);
    }

    @Bean
    public Queue topicExchangeQueueB() {
        //队列B
        return new Queue(RabbitMqConfig.TOPIC_EXCHANGE_QUEUE_B, true, false, false);
    }

    @Bean
    public Queue topicExchangeQueueC() {
        //队列C
        return new Queue(RabbitMqConfig.TOPIC_EXCHANGE_QUEUE_C, true, false, false);
    }


    @Bean
    public Binding bindTopicA() {
        //队列A绑定到FanoutExchange交换机
        return BindingBuilder.bind(topicExchangeQueueB())
                .to(rabbitmqDemoTopicExchange())
                .with("a.*");
    }

    @Bean
    public Binding bindTopicB() {
        //队列A绑定到FanoutExchange交换机
        return BindingBuilder.bind(topicExchangeQueueC())
                .to(rabbitmqDemoTopicExchange())
                .with("a.*");
    }

    @Bean
    public Binding bindTopicC() {
        //队列A绑定到FanoutExchange交换机
        return BindingBuilder.bind(topicExchangeQueueA())
                .to(rabbitmqDemoTopicExchange())
                .with("rabbit.#");
    }


    /*************Headers Exchange*************/
    @Bean
    public Queue headersQueueA() {
        return new Queue(RabbitMqConfig.HEADERS_EXCHANGE_QUEUE_A, true, false, false);
    }

    @Bean
    public Queue headersQueueB() {
        return new Queue(RabbitMqConfig.HEADERS_EXCHANGE_QUEUE_B, true, false, false);
    }

    @Bean
    public HeadersExchange rabbitmqDemoHeadersExchange() {
        return new HeadersExchange(RabbitMqConfig.HEADERS_EXCHANGE_DEMO_NAME, true, false);
    }

    @Bean
    public Binding bindHeadersA() {
        Map<String, Object> map = new HashMap<>();
        map.put("key_one", "java");
        map.put("key_two", "rabbit");
        return BindingBuilder.bind(headersQueueA())
                .to(rabbitmqDemoHeadersExchange())
                .whereAll(map).match();
    }


    @Bean
    public Binding bindHeadersB() {
        Map<String, Object> map = new HashMap<>();
        map.put("headers_A", "coke");
        map.put("headers_A", "sky");
        return BindingBuilder.bind(headersQueueB())
                .to(rabbitmqDemoHeadersExchange())
                .whereAll(map).match();
    }


}
