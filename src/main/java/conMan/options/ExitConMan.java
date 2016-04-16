package conMan.options;

import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.contactfields.Field;
import conMan.inputoutput.InputOutput;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExitConMan implements Option, Exit {
    private final ContactList contactList;
    private final File file;
    private InputOutput console;

    public ExitConMan(InputOutput console, ContactList contactList, File file) {
        this.contactList = contactList;
        this.console = console;
        this.file = file;
    }

    @Override
    public void show() {
        console.showOutput("ExitConMan ConMan \n");
    }

    @Override
    public void perform() {
        saveContacts();
        System.exit(0);
    }

    public void saveContacts() {
        for (Contact contact : contactList.getList()) {
            JSONObject jsonObject = new JSONObject();
            for (Field field : contact.fields) {
                jsonObject.put(field.showFieldName(), field.show() + " ");
            }
            writeToFile(jsonObject);
        }
    }

    private void writeToFile(JSONObject jsonObject) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(jsonObject.toString() + "\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
