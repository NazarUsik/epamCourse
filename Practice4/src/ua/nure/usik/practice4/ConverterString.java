package ua.nure.usik.practice4;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConverterString {
    private String text;

    public ConverterString(String path) throws IOException {
        text = ua.nure.usik.practice4.Converter.readFile(path);
    }

    private enum Regex{
        CYRL_REGEX("\\b[а-яёіїєА-Я]{1,}"),
        LANR_REGEX("\\b[a-zA-Z]{1,}");

        private String string;

        Regex(String string){
            this.string = string;
        }

        public String getString() {
            return string;
        }
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
            case "Latn":
                retStr = "Latn: " + convertText(Regex.LANR_REGEX.getString());
                break;
            case "latn":
                retStr = "latn: " + convertText(Regex.LANR_REGEX.getString());
                break;
            case "Cyrl":
                retStr = "Cyrl: " + convertText(Regex.CYRL_REGEX.getString());
                break;
            case "cyrl":
                retStr = "cyrl: " + convertText(Regex.CYRL_REGEX.getString());
                break;
            default:
                retStr = type + ": Incorrect input";
        }
        return retStr.trim();
    }
}
