package ua.nure.usik.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {
    private static final String ENCODING = "CP1251";

    private enum Regex {
        INT_REGEX("(?m)(\\s\\b\\d{1,}\\s)"),
        DOUBLE_REGEX("(?m)(\\.\\d\\d|\\d+\\.\\d+|\\d+\\.)"),
        CHAR_REGEX("(?m)(?U)(\\b\\w\\s)"),
        STRING_REGEX("(?U)(?m)([a-zA-zа-яА-Я]\\w{1,})");

        private String claim;

        Regex(String claim) {
            this.claim = claim;
        }

        public String getClaim() {
            return claim;
        }
    }

    private String text;

    public Converter(String path) throws IOException {
        text = readFile(path);
    }

    public static String readFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File(fileName), ENCODING);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append(System.lineSeparator());
        }
        scanner.close();
        return sb.toString().trim();
    }

    private String convertText(String reg) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(text);
        while (m.find()) {
            sb.append(m.group().trim()).append(" ");
        }
        return sb.toString();
    }

    public String typeConverter(String type) {
        String retStr;
        switch (type) {
            case "int":
                retStr = convertText(Regex.INT_REGEX.getClaim());
                break;
            case "double":
                retStr = convertText(Regex.DOUBLE_REGEX.getClaim());
                break;
            case "char":
                retStr = convertText(Regex.CHAR_REGEX.getClaim());
                break;
            case "String":
                retStr = convertText(Regex.STRING_REGEX.getClaim());
                break;
            default:
                retStr = "Incorrect input";

        }
        return retStr.trim();
    }
}
