package org.nsu.oop.task2;

import java.util.Arrays;
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
                line = line.trim();
                if (line.isEmpty()){
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
                    //System.out.println(stack + " " + variables + " " + Arrays.toString(parts));
                    Command command = CommandFactory.createCommand(commandName, firstArgument, secondArgument); //можно передвать просто массив parts? а метод сам разберется что с этим делать
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