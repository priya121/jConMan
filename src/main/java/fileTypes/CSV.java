package fileTypes;

import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.contactfields.Field;
import conMan.inputoutput.InputOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSV implements FileType {

    private final File file;
    private final InputOutput console;
    private final ContactList allContacts;

    public CSV(File file, InputOutput console, ContactList allContacts) {
        this.file = file;
        this.console = console;
        this.allContacts = allContacts;
    }

    @Override
    public void importContacts() {


    }

    @Override
    public void saveContacts() {
        try {
            for (Contact contact : allContacts.getList()) {
                FileWriter fileWriter = new FileWriter(file, true);
                for (Field field : contact.fields) {
                    fileWriter.write(field.show() + ", ");
                }
                addNewLine(fileWriter);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNewLine(FileWriter fileWriter) throws IOException {
        fileWriter.write("\n");
        fileWriter.flush();
        fileWriter.close();
    }

    @Override
    public File getFile() {
        return null;
    }
}
