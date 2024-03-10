package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;

import java.util.Map;
import java.util.Stack;

public class DevideCommand implements Command {
    @Override
    public void execute(Stack<Double> stack, Map<String, Double> variables) throws CommandException {
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
