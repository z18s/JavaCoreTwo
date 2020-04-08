package lesson_3;

import java.util.TreeMap;
import java.util.TreeSet;

public class PhoneBookMap {
    TreeMap<String, TreeSet<String>> phoneBook = new TreeMap<>();

    public void add(String name, String phoneNumber) {
        TreeSet<String> phoneNumberSet = phoneBook.containsKey(name) ? phoneBook.get(name) : new TreeSet<>();
        phoneNumberSet.add(phoneNumber);
        phoneBook.put(name, phoneNumberSet);
    }

    public TreeSet<String> get(String name) {
        return phoneBook.get(name);
    }

    @Override
    public String toString() {
        return phoneBook.toString();
    }
}