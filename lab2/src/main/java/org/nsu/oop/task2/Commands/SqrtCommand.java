package org.nsu.oop.task2.Commands;
import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;
import org.nsu.oop.task2.ExecutionContext;

import java.util.Map;
import java.util.Stack;
public class SqrtCommand implements Command {
    @Override
    public void execute(ExecutionContext context) throws CommandException {
        if (stack.isEmpty()) {
            throw new CommandException("Not enough operands on the stack for SQRT");
        }
        double operand = stack.pop();
        double result = Math.sqrt(operand);
        stack.push(result);
    }
}