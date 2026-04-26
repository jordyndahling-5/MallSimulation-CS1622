import java.util.Random;

public class Drone {
    private int health;


    Random random = new Random();

    public int getHealth() {return health;}

    public void setHealth() {
        health = random.nextInt(100);
        System.out.println("drone health = " + health);
    }
}
