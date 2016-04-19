package fileTypes;

import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.contactfields.Field;
import conMan.inputoutput.InputOutput;

import java.io.*;

public class CSVFile implements FileType {

    private final File file;
    private final InputOutput console;
    private final ContactList allContacts;

    public CSVFile(File file, InputOutput console, ContactList allContacts) {
        this.file = file;
        this.console = console;
        this.allContacts = allContacts;
    }

    @Override
    public void importContacts() {
        String csvFile = file.getPath();
        String line;
        String comma = ",";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            while ((line = reader.readLine()) != null) {
                String[] field = line.split(comma);
                String firstName = field[0];
                String lastName = field[1];
                String email = field[2];
                String homeAddress = field[3];
                String phone = field[4];
                addContactsToList(firstName, lastName, email, homeAddress, phone, console);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addContactsToList(String firstName, String lastName, String email, String homeAddress, String phone, InputOutput console) {
        Contact contact = new Contact(firstName, lastName, email, homeAddress, phone, console);
        contact.setExisting();
        allContacts.addContact(contact);
    }

    @Override
    public void saveContacts() {
        clearFileContents();
        try {
            for (Contact contact : allContacts.getList()) {
                FileWriter fileWriter = new FileWriter(file, true);
                for (Field field : contact.fields) {
                    fileWriter.write(field.get() + ",");
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

    private void clearFileContents() {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
