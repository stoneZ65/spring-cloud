package com.demo.gateway.designPattern.chainResponsibility;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 责任链 - 测试
 * @author: zhanglei
 * @date: 2021/8/4 15:37
 **/
public class DataAggregation {


    public Map convertItemDetail() {
        SkuInfoHandler skuInfoHandler = new SkuInfoHandler();
        ItemInfoHandler itemInfoHandler = new ItemInfoHandler();
        Map result = new HashMap();
        result.put("skuInfoHandler", skuInfoHandler.doRequest("模拟数据请求"));
        result.put("itemInfoHandler", itemInfoHandler.doRequest("模拟数据请求"));
        return result;
    }

    public static void main(String[] args) {
        DataAggregation dataAggregation = new DataAggregation();
        Map map = dataAggregation.convertItemDetail();
        System.out.println(JSON.toJSONString(map));
    }
}
