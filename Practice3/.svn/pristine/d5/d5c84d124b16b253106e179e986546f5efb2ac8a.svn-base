package ua.nure.usik.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static String convert(String input) {
        StringBuilder stringBuilder = new StringBuilder(input);
        Pattern pattern = Pattern.compile("\\b\\p{L}{3}");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            char firstChar = stringBuilder.charAt(matcher.start());
            if (Character.isLowerCase(firstChar)) {
                stringBuilder.setCharAt(matcher.start(), Character.toUpperCase(firstChar));
            } else {
                stringBuilder.setCharAt(matcher.start(), Character.toLowerCase(firstChar));
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] s) {
        System.out.println(convert(Part1.readFile("part3.txt")));
    }
}
