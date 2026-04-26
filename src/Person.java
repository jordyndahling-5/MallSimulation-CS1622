import java.util.ArrayList;
import java.util.Random;

public abstract class Person implements move {
    protected String name;
    protected String desire;
    protected int volumeLevel;

    Random random = new Random();
    ArrayList<Person> people = new ArrayList<>();


    public int getVolumeLevel() {return volumeLevel;}


    public int setVolumeLevel() {
        volumeLevel = random.nextInt(100);
        System.out.println("volume level = " + volumeLevel);

        return volumeLevel;
    }


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
