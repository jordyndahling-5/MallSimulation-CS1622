public class VIP extends Shopper {
    private double VIPDiscount = 0.1;

    /**
     * cuntructor - initializes name
     * @param name
     */
    public VIP(String name) {
        super(name);
    }

    /**
     * gives double value for the vip discount - vip's get special discount on items
     * @return
     */
    public double getVIPDiscount() {return VIPDiscount;}


    /**
     * things that drive a vip shopper's movement - picks random number 1-100 for hunger
     */
    @Override
    public void move() {
        hunger = random.nextInt(3);
        if (hunger == 2) {
            desire = "Food";
        } else {
            desire = "Clothing";
        }
        System.out.println("VIP " + name + "'s store desire = " + desire);

        money = 200;
        if (desire.equals("Food")) {
            money -= (10 - 10 * VIPDiscount);
            System.out.println("VIP " + name + "bought food, current money = " + money);
        } else if (desire.equals("Clothing")) {
            money -= (25 - 25 * VIPDiscount);
            System.out.println("VIP " + name + "bought food, current money = " + money);
        } else {
            System.out.println("VIP " + name + "is wandering mall");
        }
    }



}
