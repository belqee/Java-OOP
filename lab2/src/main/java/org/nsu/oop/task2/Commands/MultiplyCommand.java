package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;
import org.nsu.oop.task2.ExecutionContext;

import java.util.Map;
import java.util.Stack;

public class MultiplyCommand implements Command {
    @Override
    public void execute(ExecutionContext context) throws CommandException {
        if (stack.size() < 2) {
            throw new CommandException("Not enough operands on the stack for Multiply");
        }
        double firstOperand = stack.pop();
        double secondOperand = stack.pop();
        double result = firstOperand * secondOperand;
        stack.push(result);
    }
}
