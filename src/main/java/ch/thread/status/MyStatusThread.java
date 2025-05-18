package ch.status;

public class MyStatusThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyStatusThread："+Thread.currentThread().getState());
        // 模拟执行任务
        try {
            // 3.1 阻塞状态（Blocked）：等待锁
            synchronized (this) {
                System.out.println("线程获取到锁，继续执行...");

                // 3.2 阻塞状态（Timed Waiting）：调用sleep
                System.out.println("线程准备休眠2秒...");
                Thread.sleep(2000);

                // 3.3 阻塞状态（Waiting）：调用wait
                System.out.println("线程准备释放锁并等待...");
                this.wait();
            }
        } catch (InterruptedException e) {
            System.out.println("线程被中断：" + e.getMessage());
        } finally {
            System.out.println("线程即将终止..." + Thread.currentThread().getState());
        }
    }
}
