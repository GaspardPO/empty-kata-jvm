import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class RowTest {

    @Test
    public void should_create_of_n_seats(){
        Row row = new Row(5);

        assertThat(row.getAllSeats()).hasSize(5);
    }

    @Test
    public void should_occupy_a_seat_in_a_row(){
        // max of seats next to each other that are free.
        Row row = new Row(5);

        row.occupySeat(2);

        assertThat(row.getAllSeats()).extracting(Seat::isFree)
                .containsExactly(false, false, true, false, false );
    }


}
