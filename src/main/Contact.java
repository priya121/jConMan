package main;

public class Contact {
    private String name;

    public Contact(String name) {
       this.name = name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }
}
