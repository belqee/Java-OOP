package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;
import org.nsu.oop.task2.ExecutionContext;

import java.util.Map;
import java.util.Stack;

public class DivideCommand extends Command {
    public DivideCommand(String firstArgument,String secondArgument) throws CommandException {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }
    @Override
    public void execute(ExecutionContext context) throws CommandException {
        if (context.getStack().size() < 2) {
            throw new CommandException("Not enough operands on the stack for Devide");
        }

        double firstOperand = context.getStack().pop();
        double secondOperand = context.getStack().pop();
        if (secondOperand == 0.0){
            throw new CommandException("Division by zero");
        }
        double result = firstOperand / secondOperand;
        context.getStack().push(result);
    }
}
