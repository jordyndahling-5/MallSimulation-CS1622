import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ShopperTest {

    @Test
    public void hungerTest() {
        Random random = new Random();
        int hunger = random.nextInt(3);

        assertTrue(0 <= hunger);
        assertTrue(hunger <= 3);
    }

}