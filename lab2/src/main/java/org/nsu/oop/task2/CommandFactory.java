package org.nsu.oop.task2;

import org.nsu.oop.task2.Commands.NothingToDoCommand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, String> commandClassMap = new HashMap<>();

    static {
        try {
            String currentDir = System.getProperty("user.dir");
            String filePath = currentDir + "/src/main/java/org/nsu/oop/task2/command_config.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String commandName = parts[0].trim();
                        String className = parts[1].trim();
                        commandClassMap.put(commandName, className);
                    }
                    else throw new AnalyzingException("Error while analyzing data");
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
