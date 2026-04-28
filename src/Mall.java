import java.util.ArrayList;

public class Mall {
    private Person person;
    private Systems drones; //drones do the system work




    public static void main(String[] args) {


        Person p1 = new Shopper("Jordyn");
        p1.setVolumeLevel();
        p1.move();

        Person j1 = new Janitor();
        j1.move();

        Person s1 = new Security();
        s1.move();

        Drone d1 = new Drone();
        Systems sy1 = new Systems(d1);
        sy1.setPower();
        sy1.fixPower(d1);




    }
}
