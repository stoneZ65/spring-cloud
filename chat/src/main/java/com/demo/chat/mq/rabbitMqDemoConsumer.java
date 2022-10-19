package com.demo.chat.mq;

import com.demo.baseserver.constant.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/4 15:48
 **/
@Slf4j
@Component
public class rabbitMqDemoConsumer {


    enum Action {
        //处理成功
        SUCCESS,
        //可以重试的错误，消息重回队列
        RETRY,
        //无需重试的错误，拒绝消息，并从队列中删除
        REJECT

    }

    //使用queuesToDeclare属性，如果不存在则会创建队列
    @RabbitListener(queuesToDeclare = @Queue(
            value = RabbitMqConfig.RabbitMQ_DEMO_TOPIC,
            durable = "true",
            arguments = {
                    @Argument(name = "x-dead-letter-exchange", value = RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER_EXCHANGE),
                    @Argument(name = "x-dead-letter-routing-key", value = RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER_DIRECT_ROUTING)
            }))
    public void rabbitMqDemoProcess(Message message, Channel channel) {
        Action action = Action.SUCCESS;
        try {
            String msg = new String(message.getBody());
            System.out.println("消费者RabbitDemoConsumer从RabbitMQ服务端消费消息：" + msg);
            if ("bad".equals(msg)) {
                throw new IllegalArgumentException("测试：抛出可重回队列的异常");
            }
            if ("error".equals(msg)) {
                throw new Exception("测试：抛出无需重回队列的异常");
            }
            //模拟一个死信消息
            if ("deadletter".contains(msg)) {
                throw new RuntimeException("dead letter exception");
            }
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
            //根据异常的类型判断，设置action是可重试的，还是无需重试的
            action = Action.RETRY;
        } catch (RuntimeException e3) {
            e3.printStackTrace();
            action = Action.REJECT;
        } catch (Exception e2) {
            //打印异常
            e2.printStackTrace();
            //根据异常的类型判断，设置action是可重试的，还是无需重试的
            action = Action.REJECT;
        } finally {
            close(action, message, channel);
        }
    }

    /**
     * 死新队列接受消息
     *
     * @param msg
     * @param message
     * @param channel
     */
    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig.RABBITMQ_DEMO_DEAD_LETTER))
    public void rabbitMqDemoDeadLetterProcess(Message message, Channel channel) {
        Action action = Action.SUCCESS;
        try {
            String msg = new String(message.getBody());
            log.info("死信消息properties：{}", message.getMessageProperties());

            System.out.println("接受rabbitmqDemoDeadLetter消息A：" + msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            action = Action.RETRY;
        } finally {
            close(action, message, channel);
        }
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig.RABBITMQ_DEMO_DELAY_QUEUE_C))
    public void rabbitMqDemoDelayProcess(Message message, Channel channel) {
        Action action = Action.SUCCESS;
        try {
            String msg = new String(message.getBody());
            log.info("延迟消息properties：{}", message.getMessageProperties());

            System.out.println("接受延迟消息rabbitMqDemoDelayProcess消息C：" + msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            action = Action.RETRY;
        } finally {
            close(action, message, channel);
        }
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_A))
    public void fanoutExchangeConsumerA(Message message, Channel channel) {
        Action action = Action.SUCCESS;
        try {
            String msg = new String(message.getBody());
            System.out.println("接受fanout exchange消息A：" + msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            action = Action.RETRY;
        } finally {
            close(action, message, channel);
        }
    }

    /**
     * 手动ack，并且关闭通道
     *
     * @param action  状态
     * @param message 消息体
     * @param channel 通道
     */
    private void close(Action action, Message message, Channel channel) {
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            if (action == Action.SUCCESS) {
                //multiple 表示是否批量处理。true表示批量ack处理小于tag的所有消息。false则处理当前消息
                //deliveryTag：该消息的index
                //multiple：是否批量.。true：将一次性ack所有小于deliveryTag的消息。
                channel.basicAck(tag, false);
            } else if (action == Action.RETRY) {
                //Nack，拒绝策略，消息重回队列
                //deliveryTag：该消息的index。
                //multiple：是否批量。true：将一次性拒绝所有小于deliveryTag的消息。
                //requeue：被拒绝的是否重新入队列。
                channel.basicNack(tag, false, true);
            } else {
                //Nack，拒绝策略，并且从队列中删除
                channel.basicNack(tag, false, false);
            }
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_B))
    public void fanoutExchangeConsumerB(Message message, Channel channel) {
        Action action = Action.SUCCESS;
        try {
            String msg = new String(message.getBody());
            System.out.println("接受fanout exchange消息B：" + msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            action = Action.RETRY;
        } finally {
            close(action, message, channel);
        }
    }


    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig.TOPIC_EXCHANGE_QUEUE_A))
    public void topicExchangeConsumerA(Message message, Channel channel) {
        Action action = Action.SUCCESS;
        try {
            String msg = new String(message.getBody());
            System.out.println("接受Topic Exchange消息A：" + msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            action = Action.RETRY;
        } finally {
            close(action, message, channel);
        }
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig.TOPIC_EXCHANGE_QUEUE_B))
    public void topicExchangeConsumerB(Message message, Channel channel) {
        Action action = Action.SUCCESS;
        try {
            String msg = new String(message.getBody());
            System.out.println("接受Topic Exchange消息B：" + msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            action = Action.RETRY;
        } finally {
            close(action, message, channel);
        }
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig.TOPIC_EXCHANGE_QUEUE_C))
    public void topicExchangeConsumerC(Message message, Channel channel) {
        Action action = Action.SUCCESS;
        try {
            String msg = new String(message.getBody());
            System.out.println("接受Topic Exchange消息C：" + msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            action = Action.RETRY;
        } finally {
            close(action, message, channel);
        }
    }


    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig.HEADERS_EXCHANGE_QUEUE_A))
    public void headersExchangeConsumerA(Message message, Channel channel) {
        Action action = Action.SUCCESS;
        try {
            String contentType = message.getMessageProperties().getContentType();
            String str = new String(message.getBody(), contentType);
            System.out.println("接受Headers Exchange消息A：" + str);
        } catch (Exception ex) {
            ex.printStackTrace();
            action = Action.RETRY;
        } finally {
            close(action, message, channel);
        }
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig.HEADERS_EXCHANGE_QUEUE_B))
    public void headersExchangeConsumerB(Message message, Channel channel) {
        Action action = Action.SUCCESS;
        try {

            String contentType = message.getMessageProperties().getContentType();
            String str = new String(message.getBody(), contentType);
            System.out.println("接受Headers Exchange消息B：" + str);
        } catch (Exception ex) {
            ex.printStackTrace();
            action = Action.RETRY;
        } finally {
            close(action, message, channel);
        }
    }

}
