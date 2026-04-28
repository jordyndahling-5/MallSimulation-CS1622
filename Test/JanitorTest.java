import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class JanitorTest {

    @Test
    public void dirtTest() {
        Random random = new Random();
        int dirt = random.nextInt(10);

        assertTrue(0 <= dirt);
        assertTrue(dirt <= 10);
    }

}