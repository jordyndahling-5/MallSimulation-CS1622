import java.util.Random;

public class Shopper extends Person {
    protected double money; //amount of money a shopper has
    protected int hunger; //hunger will decide where they go?

    Random random = new Random();

    public Shopper(String name) {
        this.name = name;

    }

    /**
     * things that drive a shopper's movement
     */
    @Override
    public void move() {
        hunger = random.nextInt(3);
        System.out.println("Hunger = " + hunger);
        if (hunger == 2) {
            storeDesire = "Food"; //if people are hungry they will get food
        } else if (hunger == 0) {
            storeDesire = "Clothing"; //if people are not hungry at all they are more likely to shop
        } else {
            storeDesire = "not sure"; //if people are a little hungry (hunger = 1) they will wander
        }
        System.out.println("Shopper " + name + "'s store desire = " + storeDesire);

        money = 200;
        if (storeDesire.equals("Food")) {
            money -= 10;
            System.out.println("Shopper " + name + " bought food, current money = " + money);
        }
        else if (storeDesire.equals("Clothing")) {
            money -= 25;
            System.out.println("Shopper " + name + " bought clothes, current money = " + money);
        }
        else {
            System.out.println("Shopper " + name + " is wandering mall");
        }
    }






}
