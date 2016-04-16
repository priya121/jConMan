package test;

import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.contactfields.Field;
import conMan.inputoutput.InputOutput;
import conMan.options.Exit;
import conMan.options.Option;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FakeExit implements Option, Exit {
    private final ContactList contactList;
    private final File file;
    private InputOutput inputOutput;


    public FakeExit(InputOutput inputOutput, ContactList contactList, File file) {
        this.inputOutput = inputOutput;
        this.contactList = contactList;
        this.file = file;
    }

    @Override
    public void show() {
        System.out.println(contactList.get(0).showFields());
        saveContacts();
        inputOutput.showOutput("ExitConMan conMan.ConMan \n");
    }

    @Override
    public void perform() {
        inputOutput.showOutput("Exiting ConMan...");
    }

    public void saveContacts() {
        for (Contact contact : contactList.getList()) {
            JSONObject jsonObject = new JSONObject();
            for (Field field : contact.fields) {
                jsonObject.put(field.showFieldName(), field.show() + " ");
            }
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
}
