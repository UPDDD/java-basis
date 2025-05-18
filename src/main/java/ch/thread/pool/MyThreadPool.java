package ch.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
    public static void main(String[] args) {
        //核心线程数 最大线程数 存活时间 时间单位 阻塞队列 线程工厂 拒绝策略
        //拒绝策略 默认丢弃抛异常  丢弃不抛异常  丢弃最前面的 自定义
        //只是创建了一个对象 但是还没真正的启动
        new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }
}
