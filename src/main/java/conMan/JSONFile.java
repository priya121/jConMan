package conMan;

import conMan.contactfields.Contact;
import conMan.contactfields.Field;
import conMan.inputoutput.InputOutput;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class JSONFile implements FileType {
    private final File file;
    private final InputOutput console;
    private final ContactList allContacts;

    public JSONFile(File file, InputOutput console, ContactList allContacts) {
        this.file = file;
        this.console = console;
        this.allContacts = allContacts;
    }

    @Override
    public void importContacts() {
        JSONParser parser = new JSONParser();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            createContacts(parser, reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File getFile() {
        return file;
    }

    private void createContacts(JSONParser parser, BufferedReader reader) {
        String currentLine;
        try {
            while ((currentLine = reader.readLine()) != null) {
                JSONObject obj = (JSONObject) parser.parse(currentLine);
                String firstName = (String) obj.get("First Name: ");
                String lastName = (String) obj.get("Last Name: ");
                String email = (String) obj.get("Email: ");
                String homeAddress = (String) obj.get("Home Address: ");
                addContactsToList(firstName, lastName, email, homeAddress, console);
            }
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private void addContactsToList(String firstName, String lastName, String email, String homeAddress, InputOutput console) {
        Contact contact = new Contact(firstName, lastName, email, homeAddress, console);
        contact.setExisting();
        allContacts.addContact(contact);
    }

    @Override
    public void saveContacts() {
        clearFileContents();
        try {
            for (Contact contact : allContacts.getList()) {
                FileWriter fileWriter = new FileWriter(file, true);
                JSONObject jsonObject = contactToJSON(contact);
                writeToFile(fileWriter, jsonObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject contactToJSON(Contact contact) {
        JSONObject jsonObject = new JSONObject();
        for (Field field : contact.fields) {
            jsonObject.put(field.showFieldName(), field.show() + " ");
        }
        return jsonObject;
    }

    private void writeToFile(FileWriter fileWriter, JSONObject jsonObject) throws IOException {
        fileWriter.write(jsonObject.toString() + "\n");
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
