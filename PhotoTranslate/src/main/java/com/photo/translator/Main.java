package com.photo.translator;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static int languageID = 0;
    static String firstLangField;
    static String secondLangField;
    public static int translateToID = 0;

    public static void main(String[] args) throws Exception {
        while (languageID < 1 | languageID > 2) {
            System.out.print("Select the language you are translating from. \n 1 - Russian \n 2 - English \n");
            languageID = scan.nextInt();
        }

        //if you need other languages just change 2 letters. This translator script is suitable for all languages. But don't forget about tessdata
        switch (languageID) {
            case 1:
                firstLangField = "ru";
                secondLangField = "en";
                System.out.print("Place a photo with Russian text in the program directory and name it \"image.png\".\n");
                break;
            case 2:
                firstLangField = "en";
                secondLangField = "ru";
                System.out.print("Place a photo with English text in the program directory and name it \"image.png\".\n");
                break;
        }

        while (translateToID > 2 | translateToID < 1) {
            System.out.println("Choose how to save the translation \n 1 - as a text document \n 2 - output to the console \n");
            translateToID = scan.nextInt();
        }

        switch (translateToID) {
            case 1:
                File file = new File("translated.txt");
                PrintWriter printWriter = new PrintWriter(file);
                file.createNewFile();
                printWriter.println("Original: \n" + Translator.GetTextFromImage() + "\n" +
                        "Translation: \n" + Translator.Translate(firstLangField, secondLangField, Translator.GetTextFromImage()));
                printWriter.close();
                System.out.print("Translation saved as \"translated.txt\"");
                Thread.sleep(30000);
                break;
            case 2:
                System.out.println("Original: \n" + Translator.GetTextFromImage() + "\n" +
                        "Translation: \n" + Translator.Translate(firstLangField, secondLangField, Translator.GetTextFromImage()));
                Thread.sleep(30000);
                break;
        }
    }
}