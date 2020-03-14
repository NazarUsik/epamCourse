package ua.nure.usik.practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final String ENCODING = "CP1251";

    public static String convert(String input) {
        StringBuilder stringBuilder = new StringBuilder(input);
        Pattern pattern = Pattern.compile("\\b\\p{L}{4,}");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            for (int i = matcher.start(); i <= matcher.end(); i++) {
                char ch = stringBuilder.charAt(i);
                if (Character.isLowerCase(ch)) {
                    stringBuilder.setCharAt(i, Character.toUpperCase(ch));
                } else {
                    stringBuilder.setCharAt(i, Character.toLowerCase(ch));
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] s) {
        String line;
        StringBuilder text = new StringBuilder();
        try (BufferedReader fis = new BufferedReader(new InputStreamReader(
                new FileInputStream("part1.txt"), ENCODING))) {
            while ((line = fis.readLine()) != null) {
                text.append(line).append('\n');
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(convert(text.toString()));
    }
}
