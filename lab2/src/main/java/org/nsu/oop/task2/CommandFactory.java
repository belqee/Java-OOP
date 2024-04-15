package org.nsu.oop.task2;

import org.nsu.oop.task2.Commands.NothingToDoCommand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, String> commandClassMap = new HashMap<>();

    static {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader("command_config.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String commandName = parts[0].trim();
                        String className = parts[1].trim();
                        commandClassMap.put(commandName, className);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error while reading configuration file: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error while processing file: " + e.getMessage());;
        }
    }

    public static Command createCommand(String commandName, String firstArgument, String secondArgument) throws CommandException {
        String className = commandClassMap.get(commandName);
        if (className == null) {
            return new NothingToDoCommand();
        }
        try {
            Class<?> commandClass = Class.forName(className);
            return (Command) commandClass.getDeclaredConstructor(String.class, String.class).newInstance(firstArgument, secondArgument);
        } catch (Exception e) {
            throw new CommandException("Error while creating command: " + e.getMessage());
        }
    }
}
