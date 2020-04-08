package lesson_3;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookOOP {
    List<PhoneBookOOPItem> phoneBook = new ArrayList<>();

    public void add(String name, String phoneNumber) {
        phoneBook.add(new PhoneBookOOPItem(name, phoneNumber));
    }

    public List<String> get(String name) {
        List<String> arrSearch = new ArrayList<>();
        for (PhoneBookOOPItem item : phoneBook) {
            if (name.equals(item.getName())) {
                arrSearch.add(item.getPhone());
            }
        }
        return arrSearch;
    }

    @Override
    public String toString() {
        return phoneBook.toString();
    }
}