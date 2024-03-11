package org.nsu.oop.task2;

import junit.framework.TestCase;

public class AppTest extends TestCase {
    public void testStringCommandExecution() {
        String commands = "DEFINE x 5\n" +
                "DEFINE y 3\n" +
                "PUSH x\n" +
                "PUSH y\n" +
                "+\n" +
                "PRINT\n" +
                "PUSH x\n" +
                "PUSH y\n" +
                "-\n" +
                "PRINT\n" +
                "PUSH x\n" +
                "SQRT\n" +
                "PRINT\n";
        CommandExecutor.executeCommandsFromString(commands);

        assertEquals(2.236, CommandExecutor.Pop(), 0.001); // Ожидаемый результат для операции извлечения корня
        assertEquals(-2.0, CommandExecutor.Pop(), 0.001); // Ожидаемый результат для операции вычитания
        assertEquals(8.0, CommandExecutor.Pop(), 0.001); // Ожидаемый результат для операции сложения

    }
}