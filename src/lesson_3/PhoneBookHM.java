package lesson_3;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBookHM {
    HashMap<String, String> phoneBook = new HashMap<>();

    public void add(String name, String phoneNumber) {
        phoneBook.put(phoneNumber, name);
    }

    public ArrayList<String> get(String name) {
        ArrayList<String> arrSearch = new ArrayList<>();
        for (Object key : phoneBook.keySet()) {
            if (name.equals(phoneBook.get(key))) {
                arrSearch.add((String) key);
            }
        }
        return arrSearch;
    }

    @Override
    public String toString() {
        return phoneBook.toString();
    }
}