public class Security extends Person {

    @Override
    public void move() {
        Person p = new Shopper("example");
        people.add(p);

        if (p.volumeLevel > 75) {
            System.out.println("Security removed person for causing a scene");
            people.remove(p);
        } else {
            System.out.println("Security is monitoring");
        }
    }

}
