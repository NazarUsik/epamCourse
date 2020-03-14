package ua.nure.usik.practice4;


import java.io.IOException;
import java.util.Scanner;

public class Part3 {
    private static final String STOP = "stop";

    public static void main(String[] s) {
        Converter converter = null;
        try {
            converter = new Converter("part3.txt");
        } catch (IOException e) {
            System.out.println("Error");
        }
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            if (line.equals(STOP)) {
                break;
            }
            assert converter != null;
            String text = converter.typeConverter(line);
            if (!text.isEmpty()) {
                System.out.println(text);
            } else {
                System.out.println("No such values");
            }
        }
    }
}
