public class Security extends Person {

    /**
     * things that drive a janitor's movement - checks random number 1-100 for volumeLevel
     */
    @Override
    public void move() {
        Person p = new Shopper("example");


        if (p.volumeLevel > 75) {
            System.out.println("Security removed person for causing a scene");
        } else {
            System.out.println("Security is monitoring");
        }
    }

}
