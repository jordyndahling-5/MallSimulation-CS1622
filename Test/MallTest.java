import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MallTest {
    @Test
    public void getName() {
        Person p = new Shopper("Madi");
        String name = p.getName();
    }

}