package org.nsu.oop.task2;
import java.util.Map;
import java.util.Stack;

public abstract class Command {
    protected String firstArgument, secondArgument = null;
    public abstract void execute(ExecutionContext context) throws CommandException;
}
