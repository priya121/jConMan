package test;

import main.NameList;
import main.contactfields.Contact;
import main.inputoutput.InputOutput;
import org.junit.Test;
import test.inputOutput.FakeIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NameListTest {
    Contact Ben = createContact(Arrays.asList("Ben", "Smith", "123@gmail.com", "1 Cedar Way"));
    Contact Sarah = createContact(Arrays.asList("Sarah", "Smith", "234@gmail.com", "2 Cedar Way"));
    List<Contact> contacts = new ArrayList<>();

    @Test
    public void canFilterOutNamesInTheList() throws IOException {
        createContacts();
        InputOutput console = new FakeIO(Arrays.asList("Sarah"));
        NameList list = new NameList(contacts, console);
        List<Contact> filteredList = list.filter();
        assertEquals("Sarah Smith", filteredList.get(0).getName());
    }

    @Test
    public void canFilterOutByCombinationsOfLettersInTheList() {
        createContacts();
        InputOutput console = new FakeIO(Arrays.asList("Ben"));
        NameList list = new NameList(contacts, console);
        assertEquals("Ben Smith", list.filter().get(0).getName());
    }

    private Contact createContact(List<String> userInput) {
        InputOutput console = new FakeIO(userInput);
        return new Contact(console);
    }

    private void setContactFields(List<Contact> contacts) {
        for (Contact contact : contacts) {
            contact.setFields();
        }
    }

    private void createContacts() {
        contacts.add(Ben);
        contacts.add(Sarah);
        setContactFields(contacts);
    }
}
