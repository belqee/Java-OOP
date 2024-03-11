package org.nsu.oop.task2;

public class App {
    public static void main(String[] args) {
        if (args.length == 1) {
            String filename = args[0];
            CommandExecutor.executeCommandsFromFile(filename);
        } else {
            System.out.println("Usage: java Main <filename>");
        }
    }
}
