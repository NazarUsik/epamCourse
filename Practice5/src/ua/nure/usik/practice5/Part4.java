package ua.nure.usik.practice5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Part4 {

    private static int[][] readFileInArray(String path) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String text;
            while ((text = reader.readLine()) != null) {
                str.append(text).append('\n');
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        String[] strings = str.toString().split("\n");
        String[][] numberArr = new String[strings.length][];

        for (int i = 0; i < strings.length; i++) {
            numberArr[i] = strings[i].split(" ");
        }

        int[][] numbers = new int[numberArr.length][];

        for (int i = 0; i < numberArr.length; i++) {
            numbers[i] = new int[numberArr[i].length];
            for (int j = 0; j < numberArr[i].length; j++) {
                numbers[i][j] = Integer.parseInt(numberArr[i][j]);
            }
        }

        return numbers;
    }

    private static int findMaxInArraySimple(int[][] array) {
        int max = 0;

        int[] copy = new int[array.length*array[array.length-1].length];

        int i = 0;
        for (int[] ints : array) {
            for (int anInt : ints) {
               copy[i++] = anInt;
            }
        }

        for (int value : copy) {
            if (max < value) {
                max = value;
            }
        }

        return max;
    }

    private static int findMaxInArrayHard(int[][] array) throws InterruptedException {
        int max = 0;
        int[] maxArr = new int[array.length];

        Worker[] workers = new Worker[array.length];

        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker(array[i]);
            workers[i].start();
            workers[i].join();
        }

        for (int i = 0; i < workers.length; i++) {
            Worker w = workers[i];
            maxArr[i] = w.getMax();
            if (max < maxArr[i]) {
                max = maxArr[i];
            }
        }

        return max;
    }

    private static class Worker extends Thread {
        private int[] array;
        private int max;

        public Worker(int[] array) {
            this.array = array.clone();
        }

        private static int findMax(int[] array) {
            int[] copy = array.clone();
            Arrays.sort(copy);

            return copy[copy.length-1];
        }

        public int getMax() {
            return max;
        }

        @Override
        public void run() {
            this.max = findMax(this.array);
        }
    }

    public static void main(String[] s) throws InterruptedException {
        int[][] array = readFileInArray("part4.txt");

        long startTime = System.currentTimeMillis();
        int max = 0;

        try {
            max = findMaxInArrayHard(array);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long timeWork = System.currentTimeMillis() - startTime;

        System.out.println(max);
        System.out.println(timeWork);

        long start = System.currentTimeMillis();
        Thread.sleep(10);
        max = findMaxInArraySimple(array);
        long time = System.currentTimeMillis() - start;

        System.out.println(max);
        System.out.println(time);

    }
}
