package org.nsu.oop.task2;

import org.nsu.oop.task2.Commands.*;

public class CommandFactory {
    public static Command createCommand(String commandName, String argument) throws CommandException {
        switch (commandName) {
            case "PUSH":
                return new PushCommand(argument);
            case "POP":
                return new PopCommand(argument);
            case "SQRT":
                return new SqrtCommand();
            case "*":
                return new MultiplyCommand();
            case "-":
                return new MinusCommand();
            case "+":
                return new PlusCommand();
            case "/":
                return new DevideCommand();
            default:
                return new NullCommand();
        }
    }
}