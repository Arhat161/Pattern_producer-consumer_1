import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    // Example of pattern 'produce - consumer' with BlockingQueue
    // First thread put one random Integer to queue
    // Second thread take Integer from queue

    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); // maximum 10 threads in queue

    public static void main(String[] args) throws InterruptedException {

        // realization of thread1
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // realization of thread2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // star threads
        thread1.start();
        thread2.start();

        // join threads to main thread
        thread1.join();
        thread2.join();

    }

    // realization of Produce
    private static void produce() {
        Random random = new Random();
        while (true) {
            try {
                queue.put(random.nextInt(100)); // new digits generator
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

    }

    // realization of Consumer
    private static void consumer() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(100);
                if (random.nextInt(10) == 5) { // take digit from queue only if random.nextInt(10) == 5
                    System.out.println(queue.take()); // take digit from queue
                    System.out.println("Queue size is " + queue.size()); // print queue size
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
