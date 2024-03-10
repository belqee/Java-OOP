package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;

import java.util.Map;
import java.util.Stack;

public class PopCommand implements Command {
    private String value = "";

    public PopCommand(String value) throws CommandException {
        if (value.isEmpty()){
            throw new CommandException("Argument is Empty");
        }
        this.value = value;
    }

    @Override
    public void execute(Stack<Double> stack, Map<String, Double> variables) throws CommandException {
        if (stack.isEmpty()) {
            throw new CommandException("Stack is empty");
        }
        double result = stack.pop();
        variables.put(value, result);
    }
}
