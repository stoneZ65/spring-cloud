package com.demo.baseserver.constant;

/**
 * @description: RabbitMQ
 * @author: zhanglei
 * @date: 2022/8/4 15:08
 **/
public interface RabbitMqConfig {


    /***
     * Direct Exchange 直连交换机
     * 配置 直连交换机意思是此交换机需要绑定一个队列，
     * 要求该消息与一个特定的路由键完全匹配
     * *****/

    /**
     * RabbitMQ的主题队列
     */
    String RabbitMQ_DEMO_TOPIC = "rabbitmqDemoTopic";

    /**
     * RabbitMQ的DIRECT交换机名称
     */
    String RABBITMQ_DEMO_DIRECT_EXCHANGE = "rabbitmqDemoDirectExchange";

    /**
     * RabbitMQ的DIRECT交换机和队列绑定的匹配键DirectRouting
     */
    String RABBITMQ_DEMO_DIRECT_ROUTING = "rabbitmqDemoDirectRouting";


    /**
     *  死信队列名称
     */
    String RABBITMQ_DEMO_DEAD_LETTER =  "rabbitmqDemoDeadLetter";


    /**
     * 延时队列A
     */
    String RABBITMQ_DEMO_DELAY_QUEUE_A = "rabbitmq.demo.delay.queue.a";

    /**
     * 延时队列B
     */
    String RABBITMQ_DEMO_DELAY_QUEUE_B = "rabbitmq.demo.delay.queue.b";


    /**
     * 延时队列C
     */
    String RABBITMQ_DEMO_DELAY_QUEUE_C = "rabbitmq.demo.delay.queue.c";

    /**
     * 延时路由
     */
    String RABBITMQ_DEMO_DELAY_EXCHANGE = "rabbitmq.demo.delay.exchange";

    /**
     * 延时路由
     */
    String RABBITMQ_DEMO_DELAY_EXCHANGE_C = "rabbitmq.demo.delay.exchange.c";

    /**
     * 延时路由换机和队列绑定的匹配键DirectRouting
     */
    String RABBITMQ_DEMO_DELAY_ROUTING_A = "rabbitmq.demo.delay.routing.a";

    /**
     * 延时路由换机和队列绑定的匹配键DirectRouting
     */
    String RABBITMQ_DEMO_DELAY_ROUTING_B = "rabbitmq.demo.delay.routing.b";


    /**
     * 延时路由换机和队列绑定的匹配键DirectRouting
     */
    String RABBITMQ_DEMO_DELAY_ROUTING_C = "rabbitmq.demo.delay.routing.c";



    /**
     * 死信队列的DIRECT交换机名称
     */
    String RABBITMQ_DEMO_DEAD_LETTER_EXCHANGE = "rabbitmqDemoDeadLetterExchange";

    /**
     *死信队列的DIRECT交换机和队列绑定的匹配键DirectRouting
     */
    String RABBITMQ_DEMO_DEAD_LETTER_DIRECT_ROUTING = "rabbitmqDemoDeadLetterDirectRouting";


    /**
     * Fanout exchange
     * 一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
     * 很像子网广播，每台子网内的主机都获得了一份复制的消息。
     * 简单点说就是发布订阅
     **/

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的队列 A 的名称
     */
    String FANOUT_EXCHANGE_QUEUE_TOPIC_A = "fanout.A";

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的队列 B 的名称
     */
    String FANOUT_EXCHANGE_QUEUE_TOPIC_B = "fanout.B";

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的名称
     */
    String FANOUT_EXCHANGE_DEMO_NAME = "fanout.exchange.demo.name";


    /***
     * Topic Exchange 主题交换机(通配符交换机)
     * 直接翻译的话叫做主题交换机，如果从用法上面翻译可能叫通配符交换机会更加贴切。这种交换机是使用通配符去匹配，路由到对应的队列。
     * 通配符有两种："*" 、 "#"。需要注意的是通配符前面必须要加上"."符号。
     * 符号：* 有且只匹配一个词。比如 a.*可以匹配到"a.b"、"a.c"，但是匹配不了"a.b.c"。
     * 符号：# 匹配一个或多个词。比如"rabbit.#"既可以匹配到"rabbit.a.b"、"rabbit.a"，也可以匹配到"rabbit.a.b.c"。
     */

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机名称
     */
    String TOPIC_EXCHANGE_DEMO_NAME = "topic.exchange.demo.name";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列A的名称
     */
    String TOPIC_EXCHANGE_QUEUE_A = "topic.queue.a";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列B的名称
     */
    String TOPIC_EXCHANGE_QUEUE_B = "topic.queue.b";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列C的名称
     */
    String TOPIC_EXCHANGE_QUEUE_C = "topic.queue.c";


    /**
     * headers exchange
     * 它跟上面三种有点区别，它的路由不是用routingKey进行路由匹配，而是在匹配请求头中所带的键值进行路由
     * 创建队列需要设置绑定的头部信息，有两种模式：全部匹配和部分匹配
     * 交换机会根据生产者发送过来的头部信息携带的键值去匹配队列绑定的键值，路由到对应的队列
     * */


    /**
     * HEADERS_EXCHANGE交换机名称
     */
     String HEADERS_EXCHANGE_DEMO_NAME = "headers.exchange.demo.name";

    /**
     * RabbitMQ的HEADERS_EXCHANGE交换机的队列A的名称
     */
    String HEADERS_EXCHANGE_QUEUE_A = "headers.queue.a";

    /**
     * RabbitMQ的HEADERS_EXCHANGE交换机的队列B的名称
     */
    String HEADERS_EXCHANGE_QUEUE_B = "headers.queue.b";


}
