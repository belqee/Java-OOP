package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;
import org.nsu.oop.task2.ExecutionContext;

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
    public void execute(ExecutionContext context) throws CommandException {
        if (context.getStack().isEmpty()) {
            throw new CommandException("Stack is empty");
        }
        double result = context.getStack().pop();
        context.getVariables().put(value, result);
    }
}
