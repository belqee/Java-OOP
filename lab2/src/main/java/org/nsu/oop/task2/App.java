package org.nsu.oop.task2;

public class App {
    public static void main(String[] args) {
        if (args.length == 1) {
            String filename = args[0];
            Calculator.executeCommandsFromFile(filename);
        } else {
            System.out.println("Wrong arguments");
        }
    }
}
