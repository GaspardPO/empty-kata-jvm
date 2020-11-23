import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CellShould{

    @Test
    fun stay_dead_when_underpopulation(){
        val cell = DeadCell()

        val neigbourhoud = Underpopulation()

        val newCell = neigbourhoud.update(cell)

        assertThat(newCell).isInstanceOf(DeadCell::class.java)
    }

    @Test
    fun die_when_underpopulation(){
        val cell = LiveCell()

        val neigbourhoud = Underpopulation()

        val newCell = neigbourhoud.update(cell)

        assertThat(newCell).isInstanceOf(DeadCell::class.java)
    }

    @Test
    fun stay_dead_when_next_generation(){
        val cell = DeadCell()

        val neigbourhoud = StableNeigbourhood()

        val newCell = neigbourhoud.update(cell)

        assertThat(newCell).isInstanceOf(DeadCell::class.java)
    }

    @Test
    fun stay_alive_when_next_generation(){
        val cell = LiveCell()

        val neigbourhoud = StableNeigbourhood()

        val newCell = neigbourhoud.update(cell)

        assertThat(newCell).isInstanceOf(LiveCell::class.java)
    }

    @Test
    fun live_when_reproduction(){
        val cell = LiveCell()

        val neigbourhoud = Reproduction()

        val newCell = neigbourhoud.update(cell)

        assertThat(newCell).isInstanceOf(LiveCell::class.java)
    }

    @Test
    fun die_when_overpopulation(){
        val cell = LiveCell()

        val neigbourhoud = OverPopulation()

        val newCell = neigbourhoud.update(cell)

        assertThat(newCell).isInstanceOf(DeadCell::class.java)
    }

}