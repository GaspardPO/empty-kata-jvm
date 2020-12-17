package infra

import domain.Distributeur
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
            override fun storeEvent(evenement: Evenement, version: Int): Boolean {
                listEvent.add(evenement)
                return true
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

        val result = publisher.publish(evenement, 1)

        assertThat(handler.called).isTrue()
        assertThat(eventStore.listEvent).contains(evenement)
        assertThat(result).isTrue()
    }

    @Test
    fun return_false_when_cannot_store() {
        val evenement = Evenement.DistributeurInscrit(id = Id(0), distributeur = Distributeur("nom"))
        val eventStore = object : EventStore{
            override fun storeEvent(evenement: Evenement, version: Int) = false
        }
        val publisher = Publisher(eventStore)
        val handler = object : Handler {
            var called = false
            override fun handle(evenement: Evenement) {
                called = true
            }
        }

        publisher.register(handler)

        val result = publisher.publish(evenement, 1)

        assertThat(result).isFalse()
        assertThat(handler.called).isFalse()
    }

}
