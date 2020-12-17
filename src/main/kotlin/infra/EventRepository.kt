package infra

import domain.Evenement
import domain.Id

class EventRepository : EventStore {
    private val mutableMap : MutableMap<Id, List<Evenement>> = mutableMapOf()

    override fun storeEvent(evenement: Evenement, version: Int): Boolean {
        val previousEvents = mutableMap[evenement.id] ?: emptyList()
        return if(previousEvents.size == version){
            mutableMap[evenement.id] = previousEvents + evenement
            true
        } else false
    }

    fun load(id: Id): List<Evenement> {
        return mutableMap[id]?:emptyList()
    }
}
