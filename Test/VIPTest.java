import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VIPTest {

    @Test
    public void moneyTest() {
        double money = 200;
        String desire = "Food";
        double VIPDiscount = 0.1;
        if (desire.equals("Food")) {
            money -= (10 - 10 * VIPDiscount);
        }

        assertEquals(191, money);
    }

}