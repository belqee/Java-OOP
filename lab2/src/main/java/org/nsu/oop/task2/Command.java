package org.nsu.oop.task2;
import java.util.Map;
import java.util.Stack;

public interface Command {
    public void execute(ExecutionContext context) throws CommandException;
}
