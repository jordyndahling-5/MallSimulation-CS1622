import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MallTest {
    @Test
    public void getNameTest() {
        Person p = new Shopper("Madi");
        String name = p.getName();

        assertEquals("Madi", name);
    }

}