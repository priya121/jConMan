import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConManTest {
    ConMan conMan = new ConMan();

    @Test
    public void createReturnsAContactName() {
        assertEquals("Priya", conMan.create("Priya"));
    }

    @Test
    public void readReturnsCreatedContact() {
        conMan.create("Priya");
        assertEquals("Priya", conMan.read());
    }
}