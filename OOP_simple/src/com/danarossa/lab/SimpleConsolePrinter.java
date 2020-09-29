package com.danarossa.lab;

public class SimpleConsolePrinter extends Printer{

    @Override
    public String print(String message) {
        System.out.println(message);
        return message;
    }

    @Override
    public String print(String message, String author) {
        String s = author + " : " + message;
        System.out.println(s);
        return s;
    }
}
