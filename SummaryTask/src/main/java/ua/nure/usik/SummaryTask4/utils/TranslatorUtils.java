package ua.nure.usik.SummaryTask4.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslatorUtils {

    public static String translate(String langFrom, String langTo, String text) throws IOException {

        String urlStr =
                "https://script.google.com/macros/s/AKfycbxRzHUlFQU1vJjttMtRSxy0itqCHdWabuGI-c61NS3tPppCs0M/exec" +
                        "?q=" + URLEncoder.encode(text, "UTF-8") +
                        "&target=" + langTo +
                        "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static String translate(String langTo, String text) throws IOException{
        return translate("",langTo,text);
    }

}
