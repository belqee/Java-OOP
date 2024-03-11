package org.nsu.oop.task2;

import org.nsu.oop.task2.Commands.*;

public class CommandFactory {
    public static Command createCommand(String commandName, String firstArgument, String secondArgument) throws CommandException {
        switch (commandName) {
            case "DEFINE":
                return new DefineCommand(firstArgument, secondArgument);
            case "PRINT":
                return new PrintCommand();
            case "PUSH":
                return new PushCommand(firstArgument);
            case "POP":
                return new PopCommand(firstArgument);
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
            case "#":
                return new NothingToDoCommand();
            default:
                return new NullCommand();
        }
    }
}