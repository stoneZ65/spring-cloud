package com.demo.baseserver.server;

import com.demo.baseserver.utils.page.DataResult;

import java.util.Map;

/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/4 15:24
 **/
public interface RabbitMQService {

    DataResult<Boolean> sendMsg(String msg);


    DataResult<Boolean> sendMsgByFanoutExchange(String msg);


    DataResult<Boolean> sendMsgByTopicExchange(String msg, String routingKey);

    DataResult<Boolean> sendMsgByHeadersExchange(String msg, Map<String, Object> map);

    DataResult<Boolean> sendMsgByDelay(String msg, int delayType);

    DataResult<Boolean> sendMsgByDelay(String msg, long delayTime);

}
