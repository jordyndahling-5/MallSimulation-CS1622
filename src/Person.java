import java.util.ArrayList;

public abstract class Person implements move {
    protected String name;
    protected String desire;

    ArrayList<Person> people = new ArrayList<>();



    @Override
    public void move() {
        //move code
    }

    @Override
    public void printStatus() {
        for (Person p : people) {
            System.out.println("Name: " + p.name + " store: " + p.desire);
        } //what is this doing?
    }

}
