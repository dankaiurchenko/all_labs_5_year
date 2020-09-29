package com.danarossa.lab;

public class FormattingConsolePrinter extends Printer {
    public static final String DEFAULT_FORMAT = "message - %s";


    private final String formatString;

    public FormattingConsolePrinter(String formatString) {
        this.formatString = formatString;
    }


    private String format(String stringToPrint) {
        return String.format(formatString, stringToPrint);
    }

    @Override
    public String print(String message) {
        String output = format(message);
        System.out.println(output);
        return output;
    }

    @Override
    public String print(String message, String author) {
        String s = author + " : " + format(message);
        System.out.println(s);
        return s;
    }
}



