package com.demo.gateway.designPattern.chainResponsibility;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @description: 责任链 - 抽象处理器 具体处理方法
 * @author: zhanglei
 * @date: 2021/8/4 15:30
 **/
@Component
public class ItemInfoHandler extends AbstractDataHandler<ItemInfoHandler.ItemInfo> {

    @Override
    protected ItemInfo doRequest(String query) {
        ItemInfoHandler.ItemInfo info = new ItemInfo();
        info.setItemId(123456L);
        info.setItemName("测试A");
        return info;
    }

    @Data
    public static class ItemInfo {
        private Long itemId;
        private String itemName;
    }
}
