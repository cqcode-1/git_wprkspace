package part1.exam02;

import javax.swing.*;

/**
 * 2个生产线程，10个消费线程。
 */
public class D200421_Thread01 {

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
        int i = 0;
        private Container container;
        public Producer(Container container){
            this.container = container;
        }
        @Override
        public void run() {
//            while (true){
//                container.put(Thread.currentThread().getName() +": "+i);
//                i++;
//            }

            for (int j = 0; j < 2; j++) {
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
//            while (true){
//                System.out.println("消费: "+container.get());
//            }

            for (int j = 0; j < 3; j++) {
                System.out.println("消费: "+container.get());
            }
        }
    }

    static class Container{
        private final int SIZE = 10;
        private String[] boxes = new String[SIZE];
        private int index = 0;

        public synchronized String get(){
            while (index == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            notifyAll();
            index --;
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return boxes[index];
        }

        public synchronized void put(String i){
            while (index == SIZE){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyAll();
            System.out.println("生产："+ i);
            boxes[index] = i;
            index++;
        }

        public synchronized int getCount(){
            return boxes.length;
        }
    }
}
