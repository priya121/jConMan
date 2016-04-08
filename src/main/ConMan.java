package main;

public class ConMan {
    private String contact;

    public String create(String name) {
        contact = name;
        return name;
    }

    public String read() {
        return contact;
    }

}
