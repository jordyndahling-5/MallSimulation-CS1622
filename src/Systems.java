import java.util.Random;

public class Systems {
    private int power;
    private Drone drone;

    Random random = new Random();

    public Systems(Drone drone) {
        this.drone = drone;
    }

    public void setPower() {
        power = random.nextInt(100);
        System.out.println("Power level = " + power);
    }

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


    //drones - drones can move? (separate class?)
    //power - on or off - power source/bettery health - blackouts?
}
