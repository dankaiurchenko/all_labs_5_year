package com.danarossa.lab;

public class TimerPrinter extends Printer {
    private final int msTillPrint;
    private final Printer printer;


    public TimerPrinter(int msTillPrint, Printer printer) {
        this.msTillPrint = msTillPrint;
        this.printer = printer;
    }


    @Override
    public String print(String message) {
        try {
            Thread.sleep(msTillPrint);
            return printer.print(message);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return "";
    }

    @Override
    public String print(String message, String author) {
        try {
            Thread.sleep(msTillPrint);
            return printer.print(message, author);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return "";
    }
}
