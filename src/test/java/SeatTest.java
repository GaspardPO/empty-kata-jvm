import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SeatTest {

    @Test
    public void should_say_if_the_seat_is_free(){
        Seat aNewSeat = new Seat();

        assertThat(aNewSeat.isFree()).isTrue();
    }


    @Test
    public void should_say_that_the_seat_is_occupied(){
        Seat aNewSeat = new Seat();

        aNewSeat.occupy();

        assertThat(aNewSeat.isFree()).isFalse();
    }
}
