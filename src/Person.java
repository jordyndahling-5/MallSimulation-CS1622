public abstract class Person implements move, status {
    protected int x, y; //used for location?
    protected String name;



    @Override
    public void move() {
        //move code
    }

    @Override
    public void printStatus() {
        //print People status code
    }
}
