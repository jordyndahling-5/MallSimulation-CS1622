import java.util.Random;

public class Shopper extends Person {
    protected double money; //amount of money a shopper has
    protected int hunger; //hunger will decide where they go?

    Random random = new Random();

    public void setStoreDesire() {
        hunger = random.nextInt(3);
        if (hunger == 2) {
            storeDesire = "Food";
        } else {
            storeDesire = "Clothing";
        }
    }

    public void spendMoney() {
        money = 200;
        if (storeDesire.equals("Food")) {
            money -= 10;
            System.out.println("Shopper bought food, current money = " + money);
        } else if (storeDesire.equals("Clothing")) {
            money -= 25;
            System.out.println("Sheopper bought food, current money = " + money);
        } else {
            System.out.println("Shopper is wandering mall");
        }
    }



}
