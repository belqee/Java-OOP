package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;
import org.nsu.oop.task2.ExecutionContext;
import java.util.Stack;

public class PlusCommand extends Command {
    public PlusCommand(String firstArgument,String secondArgument) throws CommandException {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }
    @Override
    public void execute(ExecutionContext context) throws CommandException {
        Stack<Double> stack = context.getStack();
        if (stack.size() < 2) {
            throw new CommandException("Not enough operands on the stack for Plus");
        }
        double firstOperand = stack.pop();
        double secondOperand = stack.pop();
        double result = firstOperand + secondOperand;
        stack.push(result);
    }
}