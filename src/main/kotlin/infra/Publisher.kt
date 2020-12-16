package infra

import domain.Evenement

class Publisher(val eventStore : EventStore) {
    val subscribers = mutableListOf<Handler>()

    fun register(handler: Handler) {
        subscribers.add(handler)
    }

    fun publish(evenement: Evenement) {
        eventStore.storeEvent(evenement)
        subscribers.forEach{ it.handle(evenement)}
    }

}

interface EventStore {
    fun storeEvent(evenement: Evenement) :Unit
}
