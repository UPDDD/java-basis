package ch.interrupter;

public class MyThread extends Thread {
    @Override
    public void run() {
        while(true){
            System.out.println("执行run方法");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //异常会 重新设置中断标识 设置为false 终止不了了 所以需要再次调用中断
                this.interrupt();
                e.printStackTrace();
            }

            System.out.println(this.isInterrupted());

            //会清楚打断标记
            boolean interrupted = Thread.interrupted();
            if(interrupted){
                //不会清楚打断标记
                System.out.println(this.isInterrupted());
                break;
            }
        }

    }
}
