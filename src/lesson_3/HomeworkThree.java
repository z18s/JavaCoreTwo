package lesson_3;

import java.util.*;

public class HomeworkThree {
    public static void main(String[] args) {

        // Counter

        String[] arr = {"Кондор", "Лунь", "Коршун", "Ястреб", "Орёл",
                "Скопа", "Лунь", "Гарпия", "Сокол", "Канюк",
                "Коршун", "Лунь", "Гриф", "Ястреб", "Орлан",
                "Сокол", "Ястреб", "Лунь", "Гриф", "Каракара"};

        System.out.println("Original array:\t" + Arrays.toString(arr));

        HashMap<String, Integer> hm = new HashMap<>();
        for (String str : arr) {
            int i = 1;
            for (String key : hm.keySet()) {
                if (str.equals(key)) {
                    i = hm.get(key) + 1;
                }
            }
            hm.put(str, i);
        }

        System.out.println("Unique ones:\t" + hm.keySet());
        System.out.println("Count:\t" + hm);
        System.out.println();

        // Phone Book v.1

        PhoneBookHM phoneBook1 = new PhoneBookHM();
        phoneBook1.add("Smith", "555-44-60");
        phoneBook1.add("Brown", "554-35-53");
        phoneBook1.add("Jones", "552-25-63");
        phoneBook1.add("White", "559-58-31");
        phoneBook1.add("Smith", "552-39-56");
        phoneBook1.add("Johnson", "553-29-91");

        System.out.println(phoneBook1);

        String strTest = "Smith";
        System.out.printf("%s's numbers: %s%n", strTest, phoneBook1.get(strTest));

        strTest = "Brown";
        System.out.printf("%s's numbers: %s%n", strTest, phoneBook1.get(strTest));

        strTest = "Williams";
        System.out.printf("%s's numbers: %s%n", strTest, phoneBook1.get(strTest));
        System.out.println();

        // Phone Book v.2

        PhoneBookOOP phoneBook2 = new PhoneBookOOP();
        phoneBook2.add("Smith", "555-44-60");
        phoneBook2.add("Brown", "554-35-53");
        phoneBook2.add("Jones", "552-25-63");
        phoneBook2.add("White", "559-58-31");
        phoneBook2.add("Smith", "552-39-56");
        phoneBook2.add("Johnson", "553-29-91");

        System.out.println(phoneBook2);

        strTest = "Smith";
        System.out.printf("%s's numbers: %s%n", strTest, phoneBook2.get(strTest));

        strTest = "Brown";
        System.out.printf("%s's numbers: %s%n", strTest, phoneBook2.get(strTest));

        strTest = "Williams";
        System.out.printf("%s's numbers: %s%n", strTest, phoneBook2.get(strTest));
    }
}