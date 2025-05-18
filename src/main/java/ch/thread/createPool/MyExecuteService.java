package ch.thread.createPool;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyExecuteService {

    private AtomicInteger workerCount = new AtomicInteger(0);
    private AtomicInteger supprotCount = new AtomicInteger(0);
    //核心线程数
    private Integer corePoolSize;
    //最大线程数
    private Integer maximumPoolSize;
    //存活时间
    private Integer keepAliveTime;
    //时间单位
    private TimeUnit keepAliveTimeUnit;
    //阻塞队列
    BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1024);

    Thread coreTask = new Thread(() -> {
        while (true) {
            try {
                Runnable take = queue.take();
                workerCount.getAndDecrement();
                take.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    });

    Thread supportTask = new Thread(() -> {
        while (true) {
            try {
                Runnable take = queue.poll(keepAliveTime, keepAliveTimeUnit);
                if (take == null){
                    break;
                }
                supprotCount.getAndDecrement();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    });


    void execute(Runnable command) {
        Integer value = calculatedValue(workerCount,supprotCount);

        if (workerCount.get() <= corePoolSize) {
            Thread thread = new Thread(coreTask);
            thread.start();
        }
        if (queue.offer(command)) {
            return;
        }
        if (queue.size() <= maximumPoolSize - corePoolSize) {
            Thread thread = new Thread(supportTask);
            thread.start();
        }
        if (!queue.offer(command)) {
            throw new RuntimeException("队列满了");
        }

    }

    private Integer calculatedValue(AtomicInteger workerCount, AtomicInteger supprotCount) {
        return workerCount.get()+supprotCount.get();
    }
}
