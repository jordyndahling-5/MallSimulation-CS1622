import java.util.Random;

public class Janitor extends Person {

    private int dirt;
    Random random = new Random();

    /**
     * gives integer value for amount of dirt in mall
     * @return
     */
    public int getDirt() {return dirt;}

    /**
     * things that drive a janitors movement - picks random number 1-10 for dirt
     */
    @Override
    public void move() {
        desire = "Clean";
        dirt = random.nextInt(10);
        System.out.println("dirt amount = " + dirt);
        if (dirt >= 5) {
            System.out.println("Janitor is cleaning");
        }
    }


}
