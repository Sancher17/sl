package Zanyatie3.Task2;

public class TestProgram {

    public static void main(String[] args) {

        int array[] = new int[3];
        generateNumbers(array);
        printArray(array);
        sum(array);
    }

    private static void generateNumbers(int[] array) {
        int min = 100;
        int max = 1000;
        max -= min;
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * max) + min;
        }
    }

    private static void sum(int[] array) {
        int sum = 0;
        for (int anArray : array) {
            sum += parseArray(anArray);
        }
        System.out.println("Сумма первых трех цифр - "+sum);
    }

    private static int parseArray(int number) {
        char ch = String.valueOf(number).charAt(0);
        return Integer.parseInt(String.valueOf(ch));
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
