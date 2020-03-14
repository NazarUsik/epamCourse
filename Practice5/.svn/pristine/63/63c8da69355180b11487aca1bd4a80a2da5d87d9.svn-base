package ua.nure.usik.practice5;

import java.io.InputStream;

public class Part2 {

    public static void main(String[] args) throws InterruptedException {
        InputStream standardThread = System.in;
        InputStream customThread = new InputConsole();
        System.setIn(customThread);

        Thread thread = new Thread(() -> Spam.main(null));

        thread.start();
        thread.join();
        System.setIn(standardThread);
    }
}