package infra

import domain.Evenement.InscriptionDemarree
import domain.Id
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EventRepositoryShould{
    val id = Id(3)

    @Test
    fun return_true_and_store_event(){
        val eventRepository = EventRepository()

        val version : Int = 0
        val result = eventRepository.storeEvent(InscriptionDemarree(id), version)

        assertThat(result).isTrue()
        assertThat(eventRepository.load(id)).hasSize(1).containsOnly(InscriptionDemarree(id))
    }

    @Test
    fun return_false_and_not_store_event_when_version_is_not_the_right(){
        val eventRepository = EventRepository()

        val version : Int = 23
        val result = eventRepository.storeEvent(InscriptionDemarree(id), version)

        assertThat(result).isFalse()
        assertThat(eventRepository.load(id)).isEmpty()
    }

}