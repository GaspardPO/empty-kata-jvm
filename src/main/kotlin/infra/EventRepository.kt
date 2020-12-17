package infra

import domain.Evenement
import domain.Id

class EventRepository : EventStore {
    private val mutableMap : MutableMap<Id, List<Evenement>> = mutableMapOf()

    override fun storeEvent(evenement: Evenement) {
        mutableMap[evenement.id] = (mutableMap[evenement.id]?:emptyList()) + evenement
    }

    fun load(id: Id): List<Evenement> {
        return mutableMap[id]?:emptyList()
    }
}
