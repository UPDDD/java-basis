package ch.call;

import java.util.concurrent.*;

public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        MyCallable myCallable = new MyCallable();
        Future<String> submit = executor.submit(myCallable);
        String s = submit.get();
        System.out.println(s);
        executor.shutdown();



        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            //大于关闭的线程数量  能从阻塞队列中移除
            if (! isRunning(recheck) && remove(command))
                reject(command);
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        else if (!addWorker(command, false))
            reject(command);
    }
}
