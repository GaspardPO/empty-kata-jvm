import domain.*
import infra.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class IntegrationTest {

    @Test
    fun tout(){
        //given
        val id = Id(1)
        val eventList : MutableList<Evenement> = mutableListOf(Evenement.InscriptionDemarree(id))

        val projections = mutableMapOf<Id, NbInscritsProjection>()
        val handler = NbInscritsHandler(projections)

        val eventStore = object : EventStore{
            override fun storeEvent(evenement: Evenement) {
                eventList.add(evenement)
            }
        }
        val pubsub = Publisher(eventStore)
        pubsub.register(handler)

        val pastEvents = eventRepository.load(id)
        val distributionInscription = DistributionInscription(pastEvents)

        //when
        //commande : inscrire un distributeur
        val commande = Commande.InscrirePourLaDistribution(Distributeur("allaal"))
        val evenement = distributionInscription.executeCommande(commande)
        pubsub.publish(evenement!!, pastEvents.size)

        //then
        // projection sauvée avec compteur à 1
        assertThat(projections[id]!!.compteur).isEqualTo(1)
        assertThat(eventRepository.load(id)).hasSize(2)
    }
}