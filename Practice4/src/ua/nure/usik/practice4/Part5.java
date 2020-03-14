package ua.nure.usik.practice4;

import java.util.Scanner;

public class Part5 {
    private static final String STOP = "stop";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] mas = sc.nextLine().split(" ");
            if (mas[0].equals(STOP)) {
                break;
            }

            try {
                System.out.println(Translator.translate(mas[0], mas[1]));
            } catch (IndexOutOfBoundsException ex){
                System.out.println("No such values");
            }
        }
        sc.close();
    }
}
