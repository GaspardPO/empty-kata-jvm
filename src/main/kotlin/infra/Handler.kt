package infra

import domain.Evenement
import domain.Evenement.*
import domain.Id

class Handler {
    fun handle(projections: Map<Id, NbInscritsProjection>, evenement: Evenement)  : NbInscritsProjection {
        return when (evenement){
            is DistributeurInscrit -> projections.findOrNew(evenement.id).incrementCounter()
            is DistributeurDesinscrit -> projections.findOrNew(evenement.id).decrementCounter()
            else -> projections.findOrNew(evenement.id)
        }
    }

    private fun Map<Id, NbInscritsProjection>.findOrNew(id : Id) : NbInscritsProjection {
        return getOrElse(id) { NbInscritsProjection(0) }
    }

}
