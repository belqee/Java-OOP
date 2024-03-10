package org.nsu.oop.task2;

import java.util.HashMap;
import java.util.Stack;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommandExecutor {
    public static void executeCommandsFromFile(String filename) {
        Stack<Double> stack = new Stack<>();
        Map<String, Double> variables = new HashMap<String, Double>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String commandName = parts[0];
                String argument = "";
                if (parts.length > 1) {
                    argument = parts[1];
                }
                try {
                    Command command = CommandFactory.createCommand(commandName, argument);
                    command.execute(stack, variables);
                } catch (CommandException e) {
                    System.out.println("Error while executing command: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading commands from file: " + e.getMessage());
        }
    }
}