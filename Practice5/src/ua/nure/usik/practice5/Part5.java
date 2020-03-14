package ua.nure.usik.practice5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class Part5 {
    private static final int N = 10;
    private static RandomAccessFile fileWorker;


    static {
        try {
            File file = new File("part5.txt");
            if (file.exists()) {
                Files.delete(file.toPath());
            }
            fileWorker = new RandomAccessFile(file, "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String readFile() throws IOException {
        StringBuilder builder = new StringBuilder();

        fileWorker.seek(0);
        String str = fileWorker.readLine();

        while (str != null) {
            builder.append(str).append('\n');
            str = fileWorker.readLine();
        }


        fileWorker.close();

        return builder.toString().trim();
    }

    public static class Worker extends Thread {
        private static int capacity = 20;
        private int pointer;
        private int number;

        public Worker(int pointer, int number) {
            this.number = number;
            this.pointer = pointer * (capacity + 1);
        }

        private void write() throws IOException, InterruptedException {
            synchronized (Part5.class) {
                for (int i = 0; i < capacity; i++) {
                    fileWorker.seek(pointer + i);
                    fileWorker.writeBytes(String.valueOf(number));
                    Thread.sleep(1);
                }

                if (pointer / (capacity + 1) != N - 1) {
                    fileWorker.seek(pointer + capacity);
                    fileWorker.writeBytes("\n");
                }

            }
        }

        @Override
        public void run() {
            try {
                write();
            } catch (IOException | InterruptedException e) {
                System.out.println("Error in RUN!");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] s) throws InterruptedException, IOException {
        Thread[] workers = new Worker[N];
        int number = 0;

        for (int i = 0; i < workers.length; i++) {
            if (number > 9) {
                number = 0;
            }
            workers[i] = new Worker(i, number++);
        }

        for (int i = 0; i < workers.length; i++) {
            workers[i].start();
        }

        for (int i = 0; i < workers.length; i++) {
            workers[i].join();
        }


        System.out.print(Part5.readFile());
        fileWorker.close();

    }
}
