package lesson_3;

public class PhoneBookOOPItem {
    private String name;
    private String phone;

    public PhoneBookOOPItem(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return name + ": " + phone;
    }
}