package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;

import java.util.Map;
import java.util.Stack;


public class PrintCommand implements Command {
    @Override
    public void execute(Stack<Double> stack, Map<String, Double> variables) throws CommandException {
        if (stack.isEmpty()){
            throw new CommandException("Can't print, stack is empty");
        }
        double value = stack.peek();
        System.out.println(value);
    }
}
