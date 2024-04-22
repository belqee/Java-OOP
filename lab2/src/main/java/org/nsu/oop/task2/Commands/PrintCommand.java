package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;
import org.nsu.oop.task2.ExecutionContext;

import java.util.Map;
import java.util.Stack;


public class PrintCommand implements Command {
    @Override
    public void execute(ExecutionContext context) throws CommandException {
        if (stack.isEmpty()){
            throw new CommandException("Can't print, stack is empty");
        }
        double value = stack.peek();
        System.out.println(value);
    }
}
