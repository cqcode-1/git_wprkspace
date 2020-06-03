package part1.exam01;

import java.util.ArrayList;

public class D200421_Thread02 {

    static Thread t2;
    static Thread t1;
    public static void main(String[] args) {
        Container container = new Container();
        final Object o = new Object();
        t2 = new Thread(()->{
            synchronized (o){
                while (true){
                    if(container.size() == 5) {
                        System.out.println("size == 5");
                        o.notify();
                        break;
                    }else{
                        try {
                            o.notify();
                            System.out.println("t2 wait");
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        t1 = new Thread(()->{

                synchronized (o){
                    for (int i=0; i<10; i++) {
                        System.out.println("i:"+ i);
                        container.add(i);
                        if(i == 4){
                            try {
                                o.notify();
                                System.out.println("t1 notify");
                                System.out.println("t1 wait");
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                }

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
