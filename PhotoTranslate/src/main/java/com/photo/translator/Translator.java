package com.photo.translator;

import net.sourceforge.tess4j.Tesseract;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {

    public static String Translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbzN2Bq3Z2yBnOC5bp2AKMhMEwl24WogoDHCTAErkc5Gd5de8gzQCzUUamrR-FO8bwh6cQ/exec" +
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

    public static String GetTextFromImage() throws Exception{
        String text = "";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("Traineddats/"); //data for all languages https://github.com/tesseract-ocr/tessdata
        tesseract.setTessVariable("user_defined_dpi", "270");

        if (Main.languageID == 1) {
            tesseract.setLanguage("rus");
        } else {
            tesseract.setLanguage("eng");
        }
        text = tesseract.doOCR(new File("image.png"));
        return text;
    }
}
