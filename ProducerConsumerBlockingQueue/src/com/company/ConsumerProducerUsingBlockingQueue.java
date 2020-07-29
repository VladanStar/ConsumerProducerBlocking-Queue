package com.company;
import java.util.concurrent.*;

public class ConsumerProducerUsingBlockingQueue {
    private static ArrayBlockingQueue<Integer> buffer =
            new ArrayBlockingQueue<>(2);

    public static void main(String[] args) {
        // Kreiranje pula niti sa dve niti
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new ProducerTask());
        executor.execute(new ConsumerTask());
        executor.shutdown();
    }

    // Zadatak dodavanja in vrednosti u bafer
    private static class ProducerTask implements Runnable {
        public void run() {
            try {
                int i = 1;
                while (true) {
                    System.out.println("Producer writes " + i);
                    buffer.put(i++); // Add any value to the buffer, say, 1
                    // Put the thread into sleep
                    Thread.sleep((int)(Math.random() * 10000));
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Zadatak čitanja i brisanja it vrednosti iz bafera
    private static class ConsumerTask implements Runnable {
        public void run() {
            try {
                while (true) {
                    System.out.println("\t\t\tConsumer reads " + buffer.take());
                    // Put the thread into sleep
                    Thread.sleep((int)(Math.random() * 10000));
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
