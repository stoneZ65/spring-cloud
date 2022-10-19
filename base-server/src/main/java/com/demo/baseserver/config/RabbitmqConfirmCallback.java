package com.demo.baseserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * @description: rabbitmq Confirm 消息确认机制
 * @author: zhanglei
 * @date: 2022/8/8 16:52
 **/
@Slf4j
@Component
public class RabbitmqConfirmCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    /**
     * 监听消息是否到达Exchange
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息投递成功~消息Id：{}", correlationData.getId());
        } else {
            log.error("消息投递失败，Id：{}，错误提示：{}", correlationData.getId(), cause);
        }
    }

    /**
     *
     * @param message  消息体
     * @param replyCode 响应code
     * @param replyText replyText
     * @param exchange 交换机
     * @param routingKey 队列
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息没有路由到队列，获得返回的消息");
        Map map = byteToObject(message.getBody(), Map.class);
        log.info("message body: {}", map == null ? "" : map.toString());
        log.info("replyCode: {}", replyCode);
        log.info("replyText: {}", replyText);
        log.info("exchange: {}", exchange);
        log.info("routingKey: {}", exchange);
        log.info("------------> end <------------");
    }

    @SuppressWarnings("unchecked")
    private <T> T byteToObject(byte[] bytes, Class<T> clazz) {
        T t;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            t = (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }
}
