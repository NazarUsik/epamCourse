package ua.nure.usik.practice5;

public class Part3 {

    private static final int N = 10;
    private int counter = N;
    private int counter2 = 0;
    private Thread[] threads;
    private int k;
    private int t;

    public Part3(int n, int k, int t) {
        this.k = k;
        this.t = t;
        threads = new Worker[n];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Worker(counter, counter2, this.k, this.t);
        }
    }

    private static class Worker extends Thread {
        private int counter;
        private int counter2;
        private int k;
        private int t;

        public Worker(int counter, int counter2, int k, int t) {
            this.counter = counter;
            this.counter2 = counter2;
            this.k = k;
            this.t = t;
        }

        private void printCounts() {
            System.out.printf("%s %s%n", counter, counter2);
        }

        private void incrementCounter() {
            counter++;
        }

        private void incrementCounter2() {
            counter2++;
        }

        @Override
        public void run() {
            for (int i = 0; i <= k; i++) {
                printCounts();
                incrementCounter();
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                incrementCounter2();
            }
        }
    }

    private static class WorkerSynchronized extends Worker {

        public WorkerSynchronized(int counter, int counter2, int k, int t) {
            super(counter, counter2, k, t);
        }

        @Override
        public void run() {
            synchronized (Part3.class) {
                for (int i = 0; i <= super.k; i++) {
                    super.printCounts();
                    super.incrementCounter();
                    try {
                        Thread.sleep(super.t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    super.incrementCounter2();
                }
            }
        }
    }


    private void test() {
        for (Thread thread : threads) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void testSync() {
        reset();
        threads = new Thread[threads.length];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new WorkerSynchronized(counter, counter2, k, t);
        }
        test();
    }

    private void reset() {
        threads = new Thread[threads.length];
        counter = 0;
        counter2 = 0;
    }

    public static void main(String[] args) {
        Part3 p = new Part3(3, 5, 100);
        p.test();
        p.testSync();
    }
}
