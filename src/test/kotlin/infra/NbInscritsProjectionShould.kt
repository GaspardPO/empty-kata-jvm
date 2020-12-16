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
        val projections = mutableMapOf<Id, NbInscritsProjection>(id to projection)

        val handler = NbInscritsHandler(projections)

        handler.handle(Evenement.DistributeurInscrit(Distributeur("a"),id ))

        assertThat(projections[Id(0)]!!.compteur).isEqualTo(1)
    }

    @Test
    fun `decrement counter after unsubscription`(){
        val projection = NbInscritsProjection(1)
        val id = Id(0)
        val projections = mutableMapOf<Id, NbInscritsProjection>(id to projection)

        val handler = NbInscritsHandler(projections)

        handler.handle(Evenement.DistributeurDesinscrit(Distributeur("a"),id ))

        assertThat(projections[Id(0)]!!.compteur).isEqualTo(0)
    }

    @Test
    fun `n'incrémenter que la bonne projection`(){
        val projectionA = NbInscritsProjection(1)
        val idA = Id(0)

        val projectionb = NbInscritsProjection(22)
        val idb = Id(22)
        val projections = mutableMapOf<Id, NbInscritsProjection>(idA to projectionA, idb to projectionb)

        val handler = NbInscritsHandler(projections)

        handler.handle(Evenement.DistributeurDesinscrit(Distributeur("b"), idb))

        assertThat(projections[Id(22)]!!.compteur).isEqualTo(21)
    }

    @Test
    fun `should create projection`(){
        val projections = mutableMapOf<Id, NbInscritsProjection>()
        val handler = NbInscritsHandler(projections)

        handler.handle(Evenement.DistributeurInscrit(Distributeur("b"), Id(0)))

        assertThat(projections[Id(0)]!!.compteur).isEqualTo(1)
    }

    @Test
    fun `should ignore irrelevant e projection`(){
        val projections = mutableMapOf<Id, NbInscritsProjection>()
        val handler = NbInscritsHandler(projections)

        handler.handle(Evenement.InscriptionDemarree(Id(0)))

        assertThat(projections[Id(0)]).isNull()
    }

}