package ubung1;
import java.util.concurrent.Semaphore; //средство синхронизации для доступа к ресурсу

public class DiningPhilosophers {
        public static void main(String[] args) {
            int numPhilosophers = 5;
            int count=0;
            Semaphore[] spoon = new Semaphore[numPhilosophers];
            for (int i = 0; i < numPhilosophers; i++) {
                spoon[i] = new Semaphore(1);
            }

            Thread[] philosophers = new Thread[numPhilosophers];
            for (int i = 0; i < numPhilosophers; i++) {
                philosophers[i] = new Thread(new Philosopher(i, spoon[i], spoon[(i + 1) % numPhilosophers]));
                philosophers[i].start();
            }
            try {
                Thread.sleep(300000);
                for (Thread philosopher : philosophers) {
                    philosopher.interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        }
