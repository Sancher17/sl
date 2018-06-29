package Zanyatie3.Task2;

import java.util.Random;

public class TestProgram {

    public static void main(String[] args) {

        int array[] = new int[3];
        generateNumbers(array);
        printArray(array);
        sum(array);
    }

    private static void generateNumbers(int[] array) {
        for (int i = 0; i < 3; i++) {
            array[i] = (new Random().nextInt(900)+100)/100;
        }
    }

    private static void sum(int[] array) {
        int sum = 0;
        for (int anArray : array) {
            sum += anArray;
        }
        System.out.println("Сумма первых трех цифр - "+sum);
    }

    private static void printArray(int[] array) {
        for (int anArray : array) {
            System.out.println(anArray);
        }
    }
}
