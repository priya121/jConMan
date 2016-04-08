import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConManTest {

    @Test
    public void createReturnsAContactName() {
        ConMan conMan = new ConMan();
        assertEquals("Priya", conMan.create("Priya"));
    }

}