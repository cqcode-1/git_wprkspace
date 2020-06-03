package part1.exam01;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

public class D200421_Thread01 {

    static Thread t2;
    static Thread t1;
    public static void main(String[] args) {
        Container container = new Container();

        t1 = new Thread(()->{
            for (int i=0; i<10; i++) {
                container.add(i);
                System.out.println("i:"+ i);
                if(i == 4){
                    LockSupport.unpark(t2);
                    System.out.println("t1 =4");
                    LockSupport.park();
                }

            }
        });

        t2 = new Thread(()->{
            LockSupport.park();
            try {
                if(container.size() == 5){
                    LockSupport.unpark(t1);
                    System.out.println("size == 5");
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        t1.start();
        t2.start();
    }

    static class Container{
        ArrayList<Integer> boxes = new ArrayList<>();

        public int size(){
            return boxes.size();
        }

        public void add(int i){
            boxes.add(i);
        }

    }
}
