import java.util.Random;

public class Janitor extends Person {

    private int dirt;
    Random random = new Random();


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
