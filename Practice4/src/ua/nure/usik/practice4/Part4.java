package ua.nure.usik.practice4;

import java.util.NoSuchElementException;

public class Part4 {
    public static void main(String[] s) {
        ReadFile readFile = null;
        try {
            readFile = new ReadFile("part4.txt");
        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.out.println("No such file!");
        }

        assert readFile != null;
        for (String value : readFile) {
            System.out.println(value);
        }
    }
}
