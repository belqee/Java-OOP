package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;

import java.util.Map;
import java.util.Stack;

public class PushCommand implements Command {
    private String value;

    public PushCommand(String value) throws CommandException {
        if (value.isEmpty()) {
            throw new CommandException("Value is empty");
        }
        this.value = value;
    }

    @Override
    public void execute(Stack<Double> stack, Map<String, Double> variables) throws CommandException {
        double parsedValue = 0.0;
        try {
            parsedValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            if (variables.containsKey(value)) {
                parsedValue = variables.get(value);
            } else {
                throw new CommandException("Parameter doesn't exist");
            }
        }
        stack.push(parsedValue);
    }
}