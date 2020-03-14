package ua.nure.usik.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part6 {
    private static String task;
    public static Scanner in;

    public static void main(String[] args) throws FileNotFoundException {
        if ("--input".equals(args[0]) || "-i".equals(args[0])) {
            in = new Scanner(new File(args[1]));
        } else {
            throw new IllegalArgumentException();
        }
        if ("--task".equals(args[2]) || "-t".equals(args[2])) {
            task = args[3];
        } else {
            throw new IllegalArgumentException();
        }

        switchTask();
    }

    private static void switchTask() {
        switch (task) {
            case "frequency":
                Part61.main(null);
                break;
            case "length":
                Part62.main(null);
                break;
            case "duplicates":
                Part63.main(null);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
