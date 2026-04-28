import java.util.Random;

public class Drone {
    private int health;


    Random random = new Random();

    /**
     * gives integer value for health of the drone
     * @return
     */
    public int getHealth() {return health;}

    /**
     * picks random integer 1-100 for drone health
     */
    public void setHealth() {
        health = random.nextInt(100);
        System.out.println("drone health = " + health);
    }
}
