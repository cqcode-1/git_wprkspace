package part1.threadTest;


import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;

import java.util.Random;

public class FiberTest1 {

    static int count = 0;
//    static volatile int count = 0;
    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        int size = 10000;
        Fiber<Void>[] fibers = new Fiber[size];

        for (int i = 0; i < size; i++) {
            fibers[i] = new Fiber<Void>(new SuspendableRunnable() {
                @Override
                public void run() throws SuspendExecution, InterruptedException {
                    cacl(start);
                }
            });
        }

        for (int i = 0; i < size; i++) {
            fibers[i].start();
            fibers[i].join();
        }
        System.out.println("Main执行结束: "+ (System.currentTimeMillis() - start) +" count: "+count);
    }

    private static void cacl(long start) {
        int i = new Random(1000).nextInt();
        int y = new Random(10000).nextInt();
        int result = i +y;
        count ++;
        if(count == 10000){
            System.out.println("执行结束: "+ (System.currentTimeMillis() - start));
        }
    }
}
