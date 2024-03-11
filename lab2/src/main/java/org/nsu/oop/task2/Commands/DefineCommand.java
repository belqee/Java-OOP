package org.nsu.oop.task2.Commands;

import org.nsu.oop.task2.Command;
import org.nsu.oop.task2.CommandException;

import java.util.Map;
import java.util.Stack;

public class DefineCommand implements Command {
    private String firstArgument, secondArgument;
    public DefineCommand(String firstArgument,String secondArgument) throws CommandException {
        if (firstArgument.isEmpty() || secondArgument.isEmpty()){
            throw new CommandException("Wrong arguments");
        }
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }

    @Override
    public void execute(Stack<Double> stack, Map<String, Double> variables) throws CommandException {
        String name;
        double value;
        try {
            value = Double.parseDouble(secondArgument);
            value = Double.parseDouble(firstArgument);
            throw new CommandException("All arguments are double");
        } catch (NumberFormatException e){
            try {
                value = Double.parseDouble(secondArgument);
                name = firstArgument;
            } catch (NumberFormatException e2){
                value = Double.parseDouble(firstArgument);
                name = secondArgument;
            }
        }
        variables.put(name, value);
    }
}
