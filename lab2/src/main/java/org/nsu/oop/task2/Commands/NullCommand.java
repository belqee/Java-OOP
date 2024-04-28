package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;
import org.nsu.oop.task2.ExecutionContext;

import java.util.Map;
import java.util.Stack;

public class NullCommand extends Command {
    public NullCommand(String firstArgument,String secondArgument) throws CommandException {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }
    @Override
    public void execute(ExecutionContext context) throws CommandException {
        throw new CommandException("Command not found");
    }
}
