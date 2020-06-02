public class Seat {

    private boolean isFree = true;

    public void occupy() {
        isFree = false;
    }

    public boolean isFree() {
        return isFree;
    }
}
