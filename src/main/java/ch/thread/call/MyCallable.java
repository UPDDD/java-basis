package ch.call;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallable"+Thread.currentThread().getName());
        return "2";
    }
}
