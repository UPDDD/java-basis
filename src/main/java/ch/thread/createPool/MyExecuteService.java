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

    public MyExecuteService(AtomicInteger workerCount, AtomicInteger supprotCount, Integer corePoolSize, Integer maximumPoolSize, Integer keepAliveTime, TimeUnit keepAliveTimeUnit, BlockingQueue<Runnable> queue) {
        this.workerCount = workerCount;
        this.supprotCount = supprotCount;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.keepAliveTimeUnit = keepAliveTimeUnit;
        this.queue = queue;
    }




    void execute(Runnable command) {
        Integer value = calculatedValue(workerCount,supprotCount);

        if (workerCount.get() <= corePoolSize) {
            Thread thread = new CoreTask();
            thread.start();
        }
        if (queue.offer(command)) {
            return;
        }
        if (queue.size() <= maximumPoolSize - corePoolSize) {
            Thread thread = new SupportTask();
            thread.start();
        }
        if (!queue.offer(command)) {
            throw new RuntimeException("队列满了");
        }

    }
    class CoreTask extends Thread {
        public void run() {
            while (true) {
                try {
                    Runnable take = queue.take();
                    workerCount.getAndDecrement();
                    take.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    class SupportTask extends Thread {
        public void run() {
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
        }
    }

    private Integer calculatedValue(AtomicInteger workerCount, AtomicInteger supprotCount) {
        return workerCount.get()+supprotCount.get();
    }
}
