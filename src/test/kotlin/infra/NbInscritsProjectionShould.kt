package infra

import domain.Evenement
import domain.Distributeur
import domain.Id
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NbInscritsProjectionShould {

    @Test
    fun `start counter at 0`(){
        val projection = NbInscritsProjection()

        assertThat(projection.compteur).isEqualTo(0)
    }

    @Test
    fun `increment counter after subscription`(){
        val projection = NbInscritsProjection()
        val id = Id(0)
        val projections = mapOf<Id, NbInscritsProjection>(id to projection)

        val handler = Handler()

        val newProjection = handler.handle(projections, Evenement.DistributeurInscrit(Distributeur("a"),id ))

        assertThat(newProjection.compteur).isEqualTo(1)
    }

    @Test
    fun `decrement counter after unsubscription`(){
        val projection = NbInscritsProjection(1)
        val id = Id(0)
        val projections = mapOf<Id, NbInscritsProjection>(id to projection)

        val handler = Handler()

        val newProjection = handler.handle(projections, Evenement.DistributeurDesinscrit(Distributeur("a"),id ))

        assertThat(newProjection.compteur).isEqualTo(0)
    }

}