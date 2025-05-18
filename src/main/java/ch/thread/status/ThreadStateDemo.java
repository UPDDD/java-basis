package ch.status;

public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个可重入锁用于演示阻塞状态
        final Object lock = new Object();

        // 1. 新建状态（New）：线程对象已创建，但尚未调用start()
        Thread t = new Thread(() -> {
            System.out.println("线程进入就绪状态...");

            // 3. 运行状态（Running）：获取CPU执行权
            System.out.println("线程状态：" + Thread.currentThread().getState());

            // 模拟执行任务
            try {
                // 3.1 阻塞状态（Blocked）：等待锁
                synchronized (lock) {
                    System.out.println("线程获取到锁，继续执行...");

                    // 3.2 阻塞状态（Timed Waiting）：调用sleep
                    System.out.println("线程准备休眠2秒...");
                    Thread.sleep(2000);

                    // 3.3 阻塞状态（Waiting）：调用wait
                    System.out.println("线程准备释放锁并等待...");
                    lock.wait();
                }
            } catch (InterruptedException e) {
                System.out.println("线程被中断：" + e.getMessage());
            } finally {
                System.out.println("线程即将终止...");
            }
        });

        // 输出初始状态（New）
        System.out.println("创建线程后状态：" + t.getState());

        // 2. 就绪状态（Runnable）：调用start()，线程进入可运行队列
        t.start();
        System.out.println("调用start()后状态：" + t.getState());

        // 主线程休眠1秒，让子线程有机会执行
        Thread.sleep(1000);
        System.out.println("休眠1秒后子线程状态：" + t.getState());

        // 主线程获取锁并唤醒等待的子线程
        synchronized (lock) {
            System.out.println("主线程获取锁并唤醒子线程...");
            lock.notify();
        }

        // 等待子线程执行完毕
        t.join();
        System.out.println("子线程执行完毕后状态：" + t.getState());
    }
}
