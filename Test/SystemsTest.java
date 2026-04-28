import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SystemsTest {

    @Test
    public void powerTest() {
        Random random = new Random();
        int power = random.nextInt(100);

        assertTrue(0 <= power);
        assertTrue(power <= 100);
    }

}