package com.demo.gateway;

import com.demo.gateway.config.MainBusiListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(GatewayApplication.class);
        sa.addListeners(new MainBusiListeners());
        sa.run(args);
    }


}
