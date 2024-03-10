package org.nsu.oop.task2;
import java.util.Map;
import java.util.Stack;

public interface Command {
    void execute(Stack<Double> stack, Map<String, Double> variables) throws CommandException;
}

