package com.demo.gateway.queue;

import com.demo.gateway.GatewayApplication;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @description:
 * @author: zhanglei
 * @date:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayApplication.class)
public class test {

    @Autowired
    SetData setData;

    @Autowired
    TaskThread taskThread;

    @Test
    public void test() {
        taskThread.start();
        setData.run();
    }
}

@Component
class SetData implements Runnable {

    @Autowired
    private RequestQueue queue;


    @SneakyThrows
    @Override
    public void run() {
        int i = 0;
        while (true) {
            for (; i < 10; i++) {
                int finalI = i;
                Thread.sleep(3);
                queue.getTaskQueue().put(new ITask() {
                    @Override
                    public void wrok() throws InterruptedException {
                        System.out.println(Thread.currentThread().getName() + "执行了" + finalI);
                    }

                    @Override
                    public String getTaskName() {
                        return "setData";
                    }
                });
                System.out.println(finalI);
            }
        }

    }
}
