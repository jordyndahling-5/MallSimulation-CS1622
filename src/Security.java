public class Security extends Person {

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
