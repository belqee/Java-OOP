package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;
import org.nsu.oop.task2.ExecutionContext;

import java.util.Map;
import java.util.Stack;

public class DivideCommand implements Command {
    @Override
    public void execute(ExecutionContext context) throws CommandException {
        if (stack.size() < 2) {
            throw new CommandException("Not enough operands on the stack for Devide");
        }

        double firstOperand = stack.pop();
        double secondOperand = stack.pop();
        if (secondOperand == 0.0){
            throw new CommandException("Division by zero");
        }
        double result = firstOperand / secondOperand;
        stack.push(result);
    }
}
