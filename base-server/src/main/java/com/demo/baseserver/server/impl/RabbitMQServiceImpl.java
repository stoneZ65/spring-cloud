package com.demo.baseserver.server.impl;

import com.demo.baseserver.config.RabbitmqConfirmCallback;
import com.demo.baseserver.constant.DelayTypeEnum;
import com.demo.baseserver.constant.RabbitMqConfig;
import com.demo.baseserver.server.RabbitMQService;
import com.demo.baseserver.utils.page.DataResult;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.acl.LastOwnerException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/4 15:24
 **/
@Service
public class RabbitMQServiceImpl implements RabbitMQService {

    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitmqConfirmCallback rabbitmqConfirmCallback;

    @PostConstruct
    private void init() {
        if (!rabbitTemplate.isConfirmListener()) {
            rabbitTemplate.setConfirmCallback(rabbitmqConfirmCallback);
        }
        if (!rabbitTemplate.isReturnListener()) {
            rabbitTemplate.setReturnCallback(rabbitmqConfirmCallback);
        }
    }

    /**
     * 直连路由器发送
     *
     * @param msg
     * @return
     */
    @Override
    public DataResult<Boolean> sendMsg(String msg) {
        try {
            CorrelationData correlationData = getCorrelation();
            Message message = message(msg, correlationData);
            rabbitTemplate.convertAndSend(RabbitMqConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE, RabbitMqConfig.RABBITMQ_DEMO_DIRECT_ROUTING, message, correlationData);
            return DataResult.ok(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return DataResult.error(ex.getMessage());
        }
    }


    /**
     * 订阅，发布模式
     *
     * @param msg
     * @return
     */
    @Override
    public DataResult<Boolean> sendMsgByFanoutExchange(String msg) {
        try {
            CorrelationData correlationData = getCorrelation();
            Message message = message(msg, correlationData);
            rabbitTemplate.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE_DEMO_NAME, "", message, correlationData);
            return DataResult.ok(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return DataResult.error(ex.getMessage());
        }
    }


    /**
     * 主题交换机(通配符交换机)
     *
     * @param msg
     * @param routingKey
     * @return
     */
    @Override
    public DataResult<Boolean> sendMsgByTopicExchange(String msg, String routingKey) {
        try {
            CorrelationData correlationData = getCorrelation();
            Message message = message(msg, correlationData);
            rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE_DEMO_NAME, routingKey, message, correlationData);
            return DataResult.ok(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return DataResult.error(ex.getMessage());
        }
    }

    /**
     * headers exchange 匹配请求头中所带的键值进行路由
     *
     * @param msg
     * @param map
     * @return
     */
    @Override
    public DataResult<Boolean> sendMsgByHeadersExchange(String msg, Map<String, Object> map) {
        try {
            CorrelationData correlationData = getCorrelation();
            Message message = message(msg, map, correlationData);
            rabbitTemplate.convertAndSend(RabbitMqConfig.HEADERS_EXCHANGE_DEMO_NAME, null, message, correlationData);
            return DataResult.ok(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return DataResult.error(ex.getMessage());
        }
    }

    /**
     * 延时队列发送
     *
     * @param msg
     * @param delayType
     * @return
     */
    @Override
    public DataResult<Boolean> sendMsgByDelay(String msg, int delayType) {
        try {
            CorrelationData correlationData = getCorrelation();
            Message message = message(msg, correlationData);
            String routingKey = DelayTypeEnum.TEN_S.getType().equals(delayType) ?
                    RabbitMqConfig.RABBITMQ_DEMO_DELAY_ROUTING_A : RabbitMqConfig.RABBITMQ_DEMO_DELAY_ROUTING_B;
            rabbitTemplate.convertAndSend(RabbitMqConfig.RABBITMQ_DEMO_DELAY_EXCHANGE, routingKey, message, correlationData);
            return DataResult.ok(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return DataResult.error(ex.getMessage());
        }
    }

    /**
     * 延时队列发送
     *
     * @param msg 消息
     * @param delayTime 延时时间
     * @return
     */
    @Override
    public DataResult<Boolean> sendMsgByDelay(String msg, long delayTime) {
        try {
            CorrelationData correlationData = getCorrelation();
            Message message = message(msg, correlationData, (int) delayTime);
            String routingKey = RabbitMqConfig.RABBITMQ_DEMO_DELAY_ROUTING_C;
            rabbitTemplate.convertAndSend(RabbitMqConfig.RABBITMQ_DEMO_DELAY_EXCHANGE_C, routingKey, message, correlationData);
            return DataResult.ok(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return DataResult.error(ex.getMessage());
        }
    }

    /**
     *  组装消息体
     * @param msg 消息
     * @param msgId 唯一标识
     * @return
     */
    private Map<String, Object> getMessage(String msg, String msgId) {
        String sendTime = LocalDateTime.now().format(df);
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", sendTime);
        map.put("msg", msg);
        return map;
    }


    private Message message(String msg, CorrelationData correlationData) {
        return message(msg, null, correlationData, null);
    }

    private Message message(String msg, CorrelationData correlationData, Integer delayTime) {
        return message(msg, null, correlationData, delayTime);
    }

    private Message message(String msg, Map<String, Object> headers, CorrelationData correlationData) {
        return message(msg, headers, correlationData, null);
    }

    /**
     * 组合消息体
     *
     * @param msg             消息，
     * @param headers         头部信息
     * @param correlationData 消息Id对象
     * @param delayTime       延时时间
     * @return
     */
    private Message message(String msg, Map<String, Object> headers, CorrelationData correlationData, Integer delayTime) {
        MessageProperties messageProperties = new MessageProperties();
        //消息持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setContentType("utf-8");
        //添加消息头
        if (null != headers) {
            messageProperties.getHeaders().putAll(headers);
        }
        //设置延迟信息
        if (null != delayTime) {
            messageProperties.setDelay(delayTime);
        }
        //getMessage(msg, correlationData.getId());
        Message message = new Message(msg.getBytes(), messageProperties);
        return message;
    }

    /**
     * 获取唯一id
     *
     * @return
     */
    private CorrelationData getCorrelation() {
        String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(msgId);
        return correlationData;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("11", "11");
        map.put("22", "22");
        Object object = map.remove("22");
        System.out.println(object);
    }
}
