package org.nsu.oop.task2;

import java.util.HashMap;
import java.util.Stack;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Calculator {
    private static final Stack<Double> stack = new Stack<>();
    private static final Map<String, Double> variables = new HashMap<>();

    public static void executeCommandsFromFile(String filename) {
        ExecutionContext context = createContext();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            executeCommands(reader, context);
        } catch (IOException e) {
            System.err.println("Error while reading commands from file: " + e.getMessage());
        }
    }

    public static void executeCommandsFromString(String commands) {
        ExecutionContext context = createContext();
        try (BufferedReader reader = new BufferedReader(new java.io.StringReader(commands))) {
            executeCommands(reader, context);
        } catch (IOException e) {
            System.err.println("Error while reading commands from string: " + e.getMessage());
        }
    }
    private static ExecutionContext createContext() {
        return new ExecutionContext(stack, variables);
    }

    private static void executeCommands(BufferedReader reader, ExecutionContext context) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(" ");
            String commandName = parts[0];
            String firstArgument = "";
            String secondArgument = "";
            if (parts.length > 1) {
                firstArgument = parts[1];
            }
            if (parts.length > 2) {
                secondArgument = parts[2];
            }
            try {
                Command command = CommandFactory.createCommand(commandName, firstArgument, secondArgument);
                command.execute(context);
            } catch (CommandException e) {
                System.out.println("Error while executing command: " + e.getMessage());
            }
        }
    }

    public static double Pop() {
        return stack.pop();
    }

    public static void ExecuteLine(String line) {
        ExecutionContext context = createContext();
        String[] parts = line.split(" ");
        String commandName = parts[0];
        String firstArgument = "";
        String secondArgument = "";
        if (parts.length > 1) {
            firstArgument = parts[1];
        }
        if (parts.length > 2) {
            secondArgument = parts[2];
        }
        try {
            Command command = CommandFactory.createCommand(commandName, firstArgument, secondArgument);
            command.execute(context);
        } catch (CommandException e) {
            System.out.println("Error while executing command: " + e.getMessage());
        }
    }
}
