import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DroneTest {

    @Test
    public void healthTest() {
        Random random = new Random();
        int health = random.nextInt(100);

        assertTrue(0 <= health);
        assertTrue(health <= 100);
    }

}