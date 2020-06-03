package part1.exam02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2个生产线程，10个消费线程。
 */
public class D200421_Thread02 {


    public static void main(String[] args) {

        Container container = new Container();
        for (int i = 0; i <2 ; i++) {
            new Thread(new Producer(container)).start();
        }
        for (int i = 0; i <10 ; i++) {
            new Thread(new Customer(container)).start();
        }

//        new Thread(new Producer(container)).start();
//        new Thread(new Customer(container)).start();
    }

    static class Producer implements Runnable{
        private Container container;
        public Producer(Container container){
            this.container = container;
        }
        @Override
        public void run() {
            for (int j = 0; j < 10; j++) {
                container.put(Thread.currentThread().getName() +": "+j);
            }
        }
    }

    static class Customer implements Runnable{
        private Container container;
        public Customer(Container container){
            this.container = container;
        }
        @Override
        public void run() {
            for (int j = 0; j < 2; j++) {
                System.out.println("消费: "+container.get());
            }
        }
    }

    static class Container{
        private final int SIZE = 10;
        private LinkedList<String> boxes = new LinkedList<>();


       ReentrantLock reentrantLock = new ReentrantLock();
       Condition prod = reentrantLock.newCondition();
       Condition custom = reentrantLock.newCondition();

        public String get(){
            String s = null;
            try {
                reentrantLock.lock();
                while (getCount() == 0){
                    custom.await();
                }
                prod.signalAll();
                s = boxes.pollFirst();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
            return s;
        }

        public void put(String i){
            try {
                reentrantLock.lock();
                while (getCount() == SIZE){
                    prod.await();
                }
                custom.signalAll();
                System.out.println("生产："+ i);
                boxes.add(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }


        }

        public int getCount(){
            return boxes.size();
        }
    }
}
