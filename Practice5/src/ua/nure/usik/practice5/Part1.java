package ua.nure.usik.practice5;

public class Part1 {
    private static final int N = 1000;

    private static void m() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(N/3);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }

    private static class One extends Thread {
        @Override
        public void run() {
            Part1.m();
        }
    }

    private static class Two implements Runnable {

        @Override
        public void run() {
            Part1.m();
        }

    }


    public static void main(String[] args)  {

        Thread one = new One();
        one.start();
        try {
            one.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Thread two = new Thread(new Two());
        two.start();
        try {
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread three = new Thread(Part1::m);
        three.start();
        try {
            three.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
