package conMan.options;

public interface Option {
    void show();
    void perform();
    boolean contactsNecessary();
    boolean contactsExist();
}
