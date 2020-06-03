package part1.exam03;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest02 {

    static Thread t1;
    static Thread t2;
    public static void main(String[] args) {
        t1 = new Thread(()->{
            for (int i = 1; i < 27 ; i++) {
                System.out.println(i);
                LockSupport.unpark(t2);
                LockSupport.park();
            }

        });

        t2 = new Thread(()->{
            for (char i = 65; i < 91 ; i++) {
                LockSupport.park();
                System.out.println(i);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t2.start();
    }
}
