package Zanyatie4.Task1.repository;

import java.util.Arrays;

public abstract class Repository {

    public Object[] increaseArray(Object[] array) {
        int count = array.length - checkNullRow(array);
        if (array.length - count < 3) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        return array;
    }

    public int checkNullRow(Object[] array) {
        int count = 0;
        for (Object obj : array) {
            if (obj != null) {
                count++;
            }
        }
        return count;
    }

    public void deleteAll(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }
}
