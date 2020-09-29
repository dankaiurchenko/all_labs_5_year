package com.danarossa.lab;

import java.util.ArrayList;
import java.util.List;

public class HistoryPrinter extends Printer{
    private final List<String> history;
    private final Printer printer;

    public HistoryPrinter(Printer printer) {
        this.history = new ArrayList<>();
        this.printer = printer;
    }

    @Override
    public String print(String message) {
        String print = printer.print(message);
        this.history.add(print);
        return print;
    }

    @Override
    public String print(String message, String author) {
        String print = printer.print(message, author);
        this.history.add(print);
        return print;
    }


    public void printHistory(){
        System.out.println(history);
    }
}
