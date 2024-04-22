package org.nsu.oop.task2;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExecutionContext {
    private final Stack<Double> stack;
    private final Map<String, Double> variables;

    public ExecutionContext(Stack<Double> stack, Map<String, Double> variables) {
        this.stack = new Stack<>();
        this.variables = new HashMap<>();
    }

    public Stack<Double> getStack() {
        return stack;
    }

    public Map<String, Double> getVariables() {
        return variables;
    }
}