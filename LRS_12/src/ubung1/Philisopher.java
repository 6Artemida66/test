package ubung1;
import java.util.concurrent.Semaphore;
    class Philosopher implements Runnable {
        private final int id;

        private int count;
        private final Semaphore leftFork;
        private final Semaphore rightFork;

        public Philosopher(int id, Semaphore leftFork, Semaphore rightFork)
        {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        @Override
        public void run()
        {
            try
            {
                while (true)
                {
                    think();
                    takeForks();
                    eat();
                    putForks();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void think() throws InterruptedException {
            System.out.println("Философ " + id + " размышляет");
            Thread.sleep(1000);
        }

        private void takeForks() throws InterruptedException {
            leftFork.acquire();
            rightFork.acquire();
            System.out.println("Философ " + id + " взял ложку");
        }

        private void eat() throws InterruptedException {
            System.out.println("Философ " + id + " хавает");
            Thread.sleep(2000);
            count++;
            System.err.println("Философ "+id+" похавал "+count+" раз");

        }

        private void putForks() {
            leftFork.release();
            rightFork.release();
            System.out.println("Философ " + id + " кладет вилку");
        }
    }
