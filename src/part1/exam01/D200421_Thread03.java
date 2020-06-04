package part1.exam01;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

/**
 * @deprecated  不适用
 */
public class D200421_Thread03 {

    static Thread t2;
    static Thread t1;
    public static void main(String[] args) {
        Container container = new Container();
        Exchanger<Integer> exchanger = new Exchanger<>();

        t1 = new Thread(()->{
            for (int i=0; i<10; i++) {
                container.add(i);
                System.out.println("i:"+ i);
                if(i == 4){
                    try {
                        int t1Change = exchanger.exchange(5);
                        System.out.println("t1change: "+t1Change);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1 =4");
                }

            }
        });

        t2 = new Thread(()->{
            try {
                int t2Change = exchanger.exchange(0);
                System.out.println("t2change: "+t2Change);
                if(container.size() == 5){
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
