package part1.exam03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 交替打印，使用ReentrantLock 和ReentrantLock 的两个Condition
 *
 * 注意： 1.需要控制线程开始的先后顺序，谁先输出的问题
 * 2.最后一个数输出完成之后需要控制线程不要await了， 不然线程无法结束。
 *
 */
public class ReentrantLockTest04 {

    static Thread t1;
    static Thread t2;
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition one = reentrantLock.newCondition();
        Condition two = reentrantLock.newCondition();

        t1 = new Thread(()->{
            reentrantLock.lock();
            for (int i = 1; i < 27 ; i++) {
                System.out.println(i);
                try {
                    two.signalAll();
                    if(i !=26 ) //最后一个数字不阻塞了，结束线程
                        one.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reentrantLock.unlock();
        });

        t2 = new Thread(()->{
            reentrantLock.lock();
            for (char i = 65; i < 91 ; i++) {
                System.out.println(i);
                try {
                    one.signalAll();
                    if(i != 90)
                    two.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reentrantLock.unlock();
        });

        t1.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
