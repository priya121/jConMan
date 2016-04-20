package filetypes;

public interface FileType {
    void importContacts();
    void saveContacts();
    String getPath();
}
