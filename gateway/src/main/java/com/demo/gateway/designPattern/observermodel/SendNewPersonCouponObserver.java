package com.demo.gateway.designPattern.observermodel;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @description:
 * @author: zhanglei
 * @date:
 **/
public class SendNewPersonCouponObserver implements Observer {

    private ExecutorService pool = new ThreadPoolExecutor(1, 2, 1000,
            TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    @Override
    public void update(String message) {
        Future<String> future = pool.submit(new Callable<String>() {
            @SneakyThrows
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                // 处理响应的业务逻辑
                return "调用发券服务，返回结果";
            }
        });

        try {
            // 等待4000毫秒 没有获取到返回值结果则认为失败
            System.out.println(future.get(4000, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            // 执行异步获取失败
            // 记录日志，定时任务重试等
        }

        pool.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("发送新人优惠券");
        });
        pool.shutdown();
        System.out.println("执行异步返回");
    }
}
