import java.util.ArrayList;

public abstract class Person implements move, status {
    protected String name;
    protected String storeDesire;

    ArrayList<Person> people = new ArrayList<>();




    @Override
    public void move() {
        //move code
    }

    @Override
    public void printStatus() {
        for (Person p : people) {
            System.out.println("Name: " + p.name + " store: " + p.storeDesire);
        }
    }
}
