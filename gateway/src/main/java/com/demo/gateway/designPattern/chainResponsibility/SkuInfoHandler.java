package com.demo.gateway.designPattern.chainResponsibility;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhanglei
 * @date: 2021/8/4 15:34
 **/
@Component
public class SkuInfoHandler extends AbstractDataHandler<SkuInfoHandler.SkuInfo> {

    @Override
    protected SkuInfo doRequest(String query) {
        SkuInfoHandler.SkuInfo info = new SkuInfoHandler.SkuInfo();
        info.setSkuId(78910L);
        info.setSkuName("测试SKU");
        return info;
    }

    @Data
    public static class SkuInfo {
        private Long skuId;
        private String skuName;
    }
}
