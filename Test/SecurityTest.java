import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SecurityTest {

    @Test
    public void volumeLevelTest() {
        Random random = new Random();
        int volumeLevel = random.nextInt(100);

        assertTrue(0 <= volumeLevel);
        assertTrue(volumeLevel <= 100);
    }

}