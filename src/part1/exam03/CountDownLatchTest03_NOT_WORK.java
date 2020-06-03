package part1.exam03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class CountDownLatchTest03_NOT_WORK {

    static Thread t1;
    static Thread t2;
    public static void main(String[] args) {
        CountDownLatch countDownLatch1 = new CountDownLatch(10);
        CountDownLatch countDownLatch2 = new CountDownLatch(10);
        t1 = new Thread(()->{
            for (int i = 1; i < 27 ; i++) {
                System.out.println(i);
                countDownLatch2.countDown();

                System.out.println("countDownLatch2: " +countDownLatch1.getCount());
                try {
                    countDownLatch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        t2 = new Thread(()->{
            for (char i = 65; i < 91 ; i++) {
                try {
                    countDownLatch2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
                countDownLatch1.countDown();
                System.out.println("countDownLatch1: " +countDownLatch1.getCount());
            }
        });

        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }
}
