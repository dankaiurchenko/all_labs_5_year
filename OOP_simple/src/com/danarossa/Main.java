package com.danarossa;


//Design a program in any high-level language that demonstrates the concepts of OOP and contains:
//- inheritance from the abstract class;
//- public and private properties and methods;
//- overloaded methods;
//- properties:
//        - simple data types (for example, string, int ...);
//        - reference to other objects;
//        - arrays / lists of simple types / objects;
//- code to create objects of all classes, to populate all properties, and to call all methods.
//All classes, objects, their properties and methods have to make sense.


import com.danarossa.lab.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Printer[] printers = new Printer[4];

        printers[0] = new SimpleConsolePrinter();
        printers[1] = new FormattingConsolePrinter(FormattingConsolePrinter.DEFAULT_FORMAT);
        printers[2] = new TimerPrinter(1000, new FormattingConsolePrinter(FormattingConsolePrinter.DEFAULT_FORMAT));
        HistoryPrinter historyPrinter = new HistoryPrinter(new SimpleConsolePrinter());
        printers[3] = historyPrinter;

        List<String> stringsToPrint = List.of("first string", "second string", "third string", "FINAL");


        for (Printer printer : printers) {
            System.out.println(printer.getClass().getCanonicalName());
            for (int i = 0; i < stringsToPrint.size(); i++) {
                if (i == 0) {
                    printer.print(stringsToPrint.get(i), "author");
                } else {
                    printer.print(stringsToPrint.get(i));
                }
            }
            System.out.println("");
        }


        System.out.println("history");
        historyPrinter.printHistory();


    }
}
