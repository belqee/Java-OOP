package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;
import org.nsu.oop.task2.ExecutionContext;

import java.util.Map;
import java.util.Stack;


public class PrintCommand extends Command {
    public PrintCommand(String firstArgument,String secondArgument) throws CommandException {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }
    @Override
    public void execute(ExecutionContext context) throws CommandException {
        if (context.getStack().isEmpty()){
            throw new CommandException("Can't print, stack is empty");
        }
        double value = context.getStack().peek();
        System.out.println(value);
    }
}
