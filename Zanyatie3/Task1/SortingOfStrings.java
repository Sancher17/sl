package Zanyatie3.Task1;

import java.util.Arrays;
import java.util.Comparator;

public class SortingOfStrings {

    public void sortByFirstLetter(String[] stringsArray) {
        System.out.println("Сортировка по алфавиту");
        Arrays.sort(stringsArray);
    }

    public void sortByLengthString(String[] array) {
        System.out.println("Сортировка по длине строки");
        Arrays.sort(array, Comparator.comparingInt(String::length));
    }

    public void sortByQuantityOfWordsInString(String[] array) {
        System.out.println("Сортировка по количеству слов в строке");
        Comparator<String> lengthComparator = (o1, o2) -> {
            String[] str = o1.split(" ");
            int quantityFirst = str.length;
            String[] str2 = o2.split(" ");
            int quantitySecond = str2.length;
            return Integer.compare(quantityFirst, quantitySecond);
        };
        Arrays.sort(array, lengthComparator);
    }

    public void printArray(String[] array) {
        for (String anArray : array) {
            System.out.println(anArray);
        }
        System.out.println();
    }
}
