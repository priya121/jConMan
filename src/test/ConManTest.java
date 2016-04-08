package test;

import main.ConMan;
import org.junit.Assert;
import org.junit.Test;

public class ConManTest {
    ConMan conMan = new ConMan();

    @Test
    public void createReturnsAContactName() {
        Assert.assertEquals("Priya", conMan.create("Priya"));
    }

    @Test
    public void readReturnsCreatedContact() {
        conMan.create("Priya");
        Assert.assertEquals("Priya", conMan.read());
    }
}