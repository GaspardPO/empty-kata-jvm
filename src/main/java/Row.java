import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Row {

    private ArrayList<Seat> seats = new ArrayList<>();

    public Row(int rowSize) {
        IntStream.range(0, rowSize)
                .forEach(i -> seats.add(new Seat()));
    }

    public List<Seat> getAllSeats() {
        return seats;
    }

    public void occupySeat(int i) {
    }
}
