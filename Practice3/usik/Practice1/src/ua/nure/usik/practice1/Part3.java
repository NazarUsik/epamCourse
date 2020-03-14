package ua.nure.usik.practice1;

public class Part3 {
    public static void main(String[] args) {
        if (args.length != 0) {
            for (int i = 0; i < args.length - 1; i++) {
                System.out.print(args[i] + "\u0020");
            }
            System.out.print(args[args.length - 1]);
        }
    }
}