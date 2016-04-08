package test;

import main.ConMan;
import org.junit.Assert;
import org.junit.Test;

public class ConManTest {
    ConMan conMan = new ConMan();

    @Test
    public void canReadACreatedContact() {
        conMan.create("Priya");
        Assert.assertEquals("Priya", conMan.read());
    }

    @Test
    public void canUpdateACreatedContact() {
        conMan.create("Priya");
        conMan.update("Sophie");
        Assert.assertEquals("Sophie", conMan.read());
    }

    @Test
    public void canDeleteACreatedContact() {
        conMan.create("Priya");
        conMan.delete();
        Assert.assertEquals("", conMan.read());
    }
}