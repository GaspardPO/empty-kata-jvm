package infra

import domain.Distributeur
import domain.DistributionInscription
import domain.Evenement
import domain.Id
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import infra.EventStore as EventStore

class PubSubShould {

    @Test
    fun store_event_and_call_handler() {
        val evenement = Evenement.DistributeurInscrit(id = Id(0), distributeur = Distributeur("nom"))
        val eventStore = object : EventStore{
            val listEvent = mutableListOf<Evenement>()
            override fun storeEvent(evenement: Evenement) {
                listEvent.add(evenement)
            }

        }
        val publisher = Publisher(eventStore)
        val handler = object : Handler {
            var called = false
            override fun handle(evenement: Evenement) {
                called = true
            }
        }

        publisher.register(handler)

        publisher.publish(evenement)

        assertThat(handler.called).isTrue()
        assertThat(eventStore.listEvent).contains(evenement)
    }

}
