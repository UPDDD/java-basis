package ch.thread.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Detail {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(6);
        //没有返回值
        //主线程捕捉不到异常
        try{
            executor.execute(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        //有返回值
        Future<Integer> submit = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            int i= 1/0;
            return 2;
        });
        //在调用的时候才会有异常
        Integer i = submit.get();
        System.out.println(i);
        //会等所有线程执行完成后结束
        executor.shutdown();
        //会把当前核心数中的线程执行完，其他的不执行
        executor.shutdownNow();
    }
}
