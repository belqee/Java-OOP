package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;

import java.util.Map;
import java.util.Stack;

public class NothingToDoCommand implements Command {
    @Override
    public void execute(Stack<Double> stack, Map<String, Double> variables) throws CommandException {
    }
}
