package part1.exam03;

public class SynchronizedTest01 {


    public static void main(String[] args) {
        final Object lock = new Object();

        Thread t1 = new Thread(()->{
            synchronized (lock){
                for (int i = 1; i < 27 ; i++) {
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }
                lock.notify();
            }
        });

        Thread t2 = new Thread(()->{
            synchronized (lock){
                for (char i = 65; i < 91 ; i++) {
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }
                lock.notify();
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
