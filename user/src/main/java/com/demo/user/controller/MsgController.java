package com.demo.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.baseserver.server.RabbitMQService;
import com.demo.baseserver.utils.page.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/4 11:16
 **/
@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private RabbitMQService rabbitMQService;

    /**
     * url/msg/sendMsg?msg=aaaaaaaaa
     */

    @PostMapping("sendMsg")
    public DataResult<Boolean> sendMsg(@RequestParam(name = "msg") String msg) {
        return rabbitMQService.sendMsg(msg);
    }

    /**
     * url/msg/publish?msg=测试Fanout Exchange
     * @param msg
     * @return
     */
    @PostMapping("/publish")
    public DataResult<Boolean> publish(@RequestParam(name = "msg") String msg) {
        return rabbitMQService.sendMsgByFanoutExchange(msg);
    }

    /**
     * url/msg/topicSend?msg=发送topic测试&routingKey=rabbit.1111111111111
     * @param msg
     * @param routingKey
     * @return
     */
    @PostMapping("/topicSend")
    public DataResult<Boolean> topicSend(@RequestParam(name = "msg") String msg, @RequestParam(name = "routingKey") String routingKey) {
        return rabbitMQService.sendMsgByTopicExchange(msg, routingKey);
    }

    /**
     * url/msg/headersSend?msg=发送topic测试&routingKey=rabbit.1111111111111
     * @param msg
     * @param json
     * @return
     */
    @PostMapping("/headersSend")
    public DataResult<Boolean> headersSend(@RequestParam(name = "msg") String msg, @RequestParam(name = "json") String json) {
        Map map = JSONObject.parseObject(json, Map.class);
        return rabbitMQService.sendMsgByHeadersExchange(msg, map);
    }

    /**
     * url/msg/delaySend?msg=延时B：6000&delayType=1
     * @param msg
     * @param delayType 1:6000,2:60000
     * @return
     */
    @PostMapping("/delaySend")
    public DataResult<Boolean> delaySend(@RequestParam(name = "msg") String msg, @RequestParam(name = "delayType") int delayType) {
        return rabbitMQService.sendMsgByDelay(msg, delayType);
    }

    /**
     * url/msg/delayTimeSend?msg=延时C：1000&delayTime=1000
     * @param msg
     * @param delayTime
     * @return
     */
    @PostMapping("/delayTimeSend")
    public DataResult<Boolean> delayTimeSend(@RequestParam(name = "msg") String msg, @RequestParam(name = "delayTime") long delayTime) {
        return rabbitMQService.sendMsgByDelay(msg, delayTime);
    }
}
