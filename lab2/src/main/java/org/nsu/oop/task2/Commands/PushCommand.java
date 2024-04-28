package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;
import org.nsu.oop.task2.ExecutionContext;

import java.util.Map;
import java.util.Stack;

public class PushCommand extends Command {
    private String value;

    public PushCommand(String firstArgument, String secondArgument) throws CommandException {
        if (firstArgument.isEmpty()) {
            throw new CommandException("Value is empty");
        }
        this.value = firstArgument;
    }

    @Override
    public void execute(ExecutionContext context) throws CommandException {
        double parsedValue = 0.0;
        try {
            parsedValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            if (context.getVariables().containsKey(value)) {
                parsedValue = context.getVariables().get(value);
            } else {
                throw new CommandException("Parameter doesn't exist");
            }
        }
        context.getStack().push(parsedValue);
    }
}