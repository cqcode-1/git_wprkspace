package part1.exam03;

import java.util.concurrent.Semaphore;

public class SemaphoreTest05 {

    static Thread t1;
    static Thread t2;
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        t1 = new Thread(()->{
            for (int i = 1; i < 27 ; i++) {
                try {
                    semaphore.acquire();
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release(1);
                }
            }

        });

        t2 = new Thread(()->{
            for (char i = 65; i < 91 ; i++) {
                try {
                    semaphore.acquire();
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release(1);
                }

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
