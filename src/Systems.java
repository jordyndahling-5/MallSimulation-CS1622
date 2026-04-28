import java.util.Random;

public class Systems {
    private int power;
    private Drone drone;

    Random random = new Random();

    /**
     * contructor - initializes drone
     * @param drone
     */
    public Systems(Drone drone) {
        this.drone = drone;
    }

    /**
     * picks random integer between 1-100 for power level
     */
    public void setPower() {
        power = random.nextInt(100);
        System.out.println("Power level = " + power);
    }

    /**
     * decides what drone does depending on the power level and drone health
     * @param drone
     */
    public void fixPower(Drone drone) {
        if (power < 50) {
            System.out.println("power needs fixing");
            drone.setHealth();
            if (drone.getHealth() > 20) {
                System.out.println("drone is restoring power");
                power = 100;
                System.out.println("power is resotred");
            } else {
                System.out.println("drone is charging");
            }
        }
    }



}
