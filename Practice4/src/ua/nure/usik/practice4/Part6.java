package ua.nure.usik.practice4;

import java.io.IOException;
import java.util.Scanner;

public class Part6 {
    private static final String STOP1 = "stop";
    private static final String STOP2 = "Stop";

    public static void main(String[] s){
        ConverterString converter = null;
        try {
            converter = new ConverterString("part6.txt");
        } catch (IOException e){
            System.out.println("Error");
        }
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String line = in.nextLine();
            if(line.equals(STOP1) || line.equals(STOP2)){
                break;
            }
            assert converter != null;
            System.out.println(converter.typeConverter(line));
        }
    }
}
