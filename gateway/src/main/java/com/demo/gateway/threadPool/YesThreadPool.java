package com.demo.gateway.threadPool;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

/**
 * @description: 自定义线程池
 * @author: zhanglei
 * @date:
 **/
public class YesThreadPool {
    /**
     * 存放任务阻塞的队列
     */
    BlockingQueue<Runnable> taskQueue;

    //线程列表
    List<YesThread> threads;

    YesThreadPool(BlockingQueue<Runnable> queue, int threadSize) {
        this.taskQueue = queue;
        threads = new ArrayList<>(threadSize);


        //初始化线程，定义名称
        IntStream.rangeClosed(1, threadSize).forEach((i) -> {
            YesThread yesThread = new YesThread("yes-task-thread-" + i);
            yesThread.start();
            threads.add(yesThread);
        });
    }

    /**
     * 提交任务往任务队列中插入任务
     */
    public void execute(Runnable runnable) throws InterruptedException {
        taskQueue.put(runnable);
    }



    class YesThread extends Thread {
        public YesThread(String name) {
            super(name);
        }

        @SneakyThrows
        @Override
        public void run() {
            Runnable runnable;
            while (true) {
                runnable = taskQueue.take();
                runnable.run();
            }
        }
    }

    public static void main(String[] args) {
        YesThreadPool pool = new YesThreadPool(new LinkedBlockingQueue<>(10), 3);
        IntStream.rangeClosed(1, 5).forEach(i -> {
            try {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + ":abc");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
