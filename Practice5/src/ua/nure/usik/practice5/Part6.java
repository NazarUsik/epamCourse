package ua.nure.usik.practice5;

public class Part6 {
    public static final int N = 10;
    private static final Object M = new Object();

    private static class Worker extends Thread {
        @Override
        public void run() {
            synchronized (M) {
                    try {
                        M.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Worker();
        t.start();
        t.join(N);
        Thread t2;

        synchronized (M) {
            M.notifyAll();
            System.out.println(t.getState());
        }

        t2 = new Worker();
        t2.start();
        t2.join(N);
        System.out.println(t2.getState());
        System.out.println(t.getState());

        synchronized (M) {
            M.notifyAll();
            t.interrupt();
            t2.interrupt();
        }
    }
}
