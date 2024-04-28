package org.nsu.oop.task2.Commands;
import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;
import org.nsu.oop.task2.ExecutionContext;

import java.util.Map;
import java.util.Stack;
public class SqrtCommand extends Command {
    public SqrtCommand(String firstArgument,String secondArgument) throws CommandException {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }
    @Override
    public void execute(ExecutionContext context) throws CommandException {
        if (context.getStack().isEmpty()) {
            throw new CommandException("Not enough operands on the stack for SQRT");
        }
        double operand = context.getStack().pop();
        double result = Math.sqrt(operand);
        context.getStack().push(result);
    }
}