package lesson_2;

import java.util.Arrays;

public class HomeworkTwo {
    public static void main(String[] args) {
        String[][] array0;      // true
        String[][] array1;      // exc size
        String[][] array2;      // exc size
        String[][] array3;      // exc data

        System.out.println("--------------------");

        array0 = new String[][]{{"11", "12", "13", "14"},
                                {"21", "22", "23", "24"},
                                {"31", "32", "33", "34"},
                                {"41", "42", "43", "44"}};
        arrayPrint(array0);

        try {
            System.out.printf("The sum of array elements is %d.%n", arrayCalculator(array0));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------");

        array1 = new String[][]{{"11", "12", "13", "14"},
                                {"21", "22", "23", "24"},
                                {"31", "32", "33", "34"}};
        arrayPrint(array1);

        try {
            System.out.printf("The sum of array elements is %d.%n", arrayCalculator(array1));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------");

        array2 = new String[][]{{"11", "12", "13", "14"},
                                {"21", "22", "23"},
                                {"31", "32", "33", "34"},
                                {"41", "42", "43", "44"}};
        arrayPrint(array2);

        try {
            System.out.printf("The sum of array elements is %d.%n", arrayCalculator(array2));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------");

        array3 = new String[][]{{"11", "12", "13", "14"},
                                {"21", "22", "23", "24"},
                                {"31", "Zz", "33", "34"},
                                {"41", "42", "43", "44"}};
        arrayPrint(array3);

        try {
            System.out.printf("The sum of array elements is %d.%n", arrayCalculator(array3));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void arrayPrint(String[][] stringArray) {
        for (String[] strings : stringArray) {
            System.out.println(Arrays.toString(strings));
        }
        System.out.println();
    }

    public static int arrayCalculator(String[][] stringArray) throws MyArraySizeException, MyArrayDataException {
        final int SIZE = 4;

        if (stringArray.length == SIZE) {
            for (String[] strings : stringArray) {
                if (strings.length != SIZE) {
                    throw new MyArraySizeException(SIZE);
                }
            }
        } else {
            throw new MyArraySizeException(SIZE);
        }

        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                try {
                    sum += Integer.parseInt(stringArray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i + 1, j + 1, stringArray[i][j]);
                }
            }
        }
        return sum;
    }
}

class MyArraySizeException extends RuntimeException {
    public MyArraySizeException(int size) {
        super("Array size exception: size " + size + " х " + size + " needed.");
    }
}

class MyArrayDataException extends RuntimeException {
    public MyArrayDataException(int row, int col, String str) {
        super("Array data exception: integer needed. There is <" + str + "> in row " + row + " column " + col + ".");
    }
}