import java.util.ArrayList;
import java.util.Random;

public abstract class Person implements move {
    protected String name;
    protected String desire;
    protected int volumeLevel;

    Random random = new Random();

    /**
     * gives name
     * @return
     */
    public String getName() {return name;}

    /**
     * sets String value to name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gives desire of person (food, clothing, cleaning, etc)
     * @return
     */
    public String getDesire() {return desire;}

    /**
     * sets String value to desire
     * @param desire
     */
    public void setDesire(String desire) {
        this.desire = desire;
    }

    /**
     * gives number value for a person's volume level
     * @return
     */
    public int getVolumeLevel() {return volumeLevel;}

    /**
     * sets a random number between 1-100 for the volume level
     * @return
     */
    public int setVolumeLevel() {
        volumeLevel = random.nextInt(100);
        System.out.println("volume level = " + volumeLevel);

        return volumeLevel;
    }

    /**
     * no specific move() method for Person class, specific in child classes
     */
    @Override
    public void move() {
        //move code (is done in each subclass)
    }


}
