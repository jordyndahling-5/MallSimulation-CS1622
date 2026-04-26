import java.util.ArrayList;

public class Main {
    private Person person; //make this a list?
    private Store store; //make this a list?
    private Systems drones; //drones do the system work




    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();

        Person p1 = new Shopper("Jordyn");
        p1.setVolumeLevel();
        p1.move();
        people.add(p1);

        Person j1 = new Janitor();
        j1.move();

        Person s1 = new Security();
        people.add(s1);
        s1.move();



    }
}
