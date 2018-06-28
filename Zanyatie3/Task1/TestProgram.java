package Zanyatie3.Task1;

public class TestProgram {

    public static void main(String[] args) {

         String [] array = {
                 "ccccccccc aaaaaaa bbbbbb",
                 "bbbbbbbbbbbbb ddddddddd",
                 "ddddddddddd",
                 "rrrrrrrrrr",
                 "tt ddd ff gg gg",
                 "aaaaaaaa wewe rrrr"
         };

        SortingOfStrings strings = new SortingOfStrings();

        strings.sortByFirstLetter(array);
        strings.printArray(array);

        strings.sortByLengthString(array);
        strings.printArray(array);

        strings.sortByQuantityOfWordsInString(array);
        strings.printArray(array);
    }
}
