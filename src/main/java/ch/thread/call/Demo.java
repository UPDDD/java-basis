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
    }
}
