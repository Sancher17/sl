package Zanyatie3.Task1;

public class TestProgram {

    public static void main(String[] args) {

         String [] array = {
                 "ccccccccc aaaaaaa bbbbbb",
                 "bbbbbbbbbbbbb ddddddddd",
                 "ddddddddddd",
                 "rrrrrrrrrr",
                 "aaaaaaaa wewe rrrr"
         };

        SortingOfStrings strings = new SortingOfStrings();

        strings.sortByFirstLetter(array);
        strings.printArray(array);
    }
}
