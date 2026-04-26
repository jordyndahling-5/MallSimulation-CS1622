public class VIP extends Shopper {
    private double VIPDiscount = 0.1;


    @Override
    public void spendMoney() {
        money = 200;
        if (storeDesire.equals("Food")) {
            money -= (10 - 10 * VIPDiscount);
            System.out.println("Shopper bought food, current money = " + money);
        } else if (storeDesire.equals("Clothing")) {
            money -= (25 - 25 * VIPDiscount);
            System.out.println("Sheopper bought food, current money = " + money);
        } else {
            System.out.println("Shopper is wandering mall");
        }
    }



}
