package part1.threadTest;


import java.util.Random;

public class ThreadTest1 {

    static int count = 0;
//    static volatile int count = 0;
    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        int size = 10000;
        Thread[] threads = new Thread[size];

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = new Random(1000).nextInt();
                int y = new Random(10000).nextInt();
                int result = i +y;
                count ++;
                if(count == 10000){
                    System.out.println("执行结束: "+ (System.currentTimeMillis() - start));
                }
            }
        };

        for (int i = 0; i < size; i++) {

            threads[i] = new Thread(runnable);
        }

        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(runnable);
        }

        for (int i = 0; i < size; i++) {
            threads[i].start();
            threads[i].join();
        }
        System.out.println("Main执行结束: "+ (System.currentTimeMillis() - start) +" count: "+count);
    }
}
