package ch.interrupter;

public class Demo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();


        myThread.start();
        //线程中断
        myThread.interrupt();
    }
}
