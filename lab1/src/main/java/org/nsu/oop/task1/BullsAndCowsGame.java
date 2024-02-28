package org.nsu.oop.task1;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BullsAndCowsGame {
    private final int[] number = new int[4];
    private final int[] inputNumber = new int[4];

    public BullsAndCowsGame() {
        generateNumber();
    }

    private void Scan(Scanner scanner){
        String input = scanner.nextLine();
        if (input.length() != 4){
            System.out.println("The length of given number doesn't match, try again");
            Scan(scanner);
        } else {
            for (int i = 0; i < 4; ++i) {
                inputNumber[i] = (int)(input.charAt(i) - '0');
            }
        }
    }

    public void start(){
        //System.out.printf(Arrays.toString(number));
        System.out.println("The game starts! Enter the number");
        Scanner scanner = new Scanner(System.in);
        do {
            Scan(scanner);
        } while (!checkInput());
    }

    private void generateNumber(){
        Random random = new Random();
        for (int i= 0; i < 4; ++i){
            number[i] = random.nextInt(10);
        }
    }

    private boolean checkInput() {
        int bulls = 0;
        int cows = 0;
        int[] counter = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 4;++i){
            if (number[i] == inputNumber[i]) bulls++;
            for (int j = 0; j < 4; ++j){
                if (number[i] == inputNumber[j]){
                    ++counter[number[i]];
                }
            }
        }
        for (int j : counter) {
            if (j > 0) cows++;
        }
        if (bulls != 4){
            System.out.printf("Cows: %d\nBulls: %d\n", cows, bulls);
        } else System.out.println("You win!");
        return bulls == 4;
    }
}
