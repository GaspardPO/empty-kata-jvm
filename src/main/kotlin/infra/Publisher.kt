package infra

import domain.Evenement

class Publisher(val eventStore : EventStore) {
    val subscribers = mutableListOf<Handler>()

    fun register(handler: Handler) {
        subscribers.add(handler)
    }

    fun publish(evenement: Evenement, version: Int): Boolean {
        val stored = eventStore.storeEvent(evenement, version)
        if (stored) {
            subscribers.forEach { it.handle(evenement) }
        }
        return stored
    }

}

interface EventStore {
    fun storeEvent(evenement: Evenement)
}
