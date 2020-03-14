package ua.nure.usik.practice5;

import java.util.Scanner;

public class Spam {
    private Thread[] threads;

    public Spam(String[] messages, int[] times) {
        if(messages.length != times.length){
            throw new IllegalArgumentException();
        }
        this.threads = new Thread[messages.length];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Worker(messages[i], times[i]);
        }
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private static class Worker extends Thread {
        private int time;
        private String message;

        public Worker(String message, int time) {
            this.time = time;
            this.message = message;
        }

        public void run() {
            while (true) {
                System.out.println(message);
                try {
                    sleep(time);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }




    public static void main(String[] args){
        Spam spam = new Spam(new String[]{"@@@", "bbbbbbb", "gfd", "453", "11111", "22222"},
                new int[]{333, 222, 1111, 432, 222, 222});
        spam.start();
        Scanner reader = new Scanner(System.in);
        while (true) {
            if (reader.nextLine().isEmpty()) {
                spam.stop();
                break;
            }
        }
    }

}

