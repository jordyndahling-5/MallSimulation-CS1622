import java.util.ArrayList;
import java.util.Random;

public abstract class Person implements move {
    protected String name;
    protected String desire;
    protected int volumeLevel;

    Random random = new Random();


    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public String getDesire() {return desire;}
    public void setDesire(String desire) {
        this.desire = desire;
    }

    public int getVolumeLevel() {return volumeLevel;}


    public int setVolumeLevel() {
        volumeLevel = random.nextInt(100);
        System.out.println("volume level = " + volumeLevel);

        return volumeLevel;
    }


    @Override
    public void move() {
        //move code (is done in each subclass)
    }


}
