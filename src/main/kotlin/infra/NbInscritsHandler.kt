package infra

import domain.Evenement
import domain.Evenement.*
import domain.Id

class NbInscritsHandler(val projections: MutableMap<Id, NbInscritsProjection>) : Handler{
    override fun handle(evenement: Evenement)  {
        when (evenement){
            is DistributeurInscrit -> projections[evenement.id] = projections.findOrNew(evenement.id).incrementCounter()
            is DistributeurDesinscrit -> projections[evenement.id] = projections.findOrNew(evenement.id).decrementCounter()
        }
    }

    private fun Map<Id, NbInscritsProjection>.findOrNew(id : Id) : NbInscritsProjection {
        return getOrElse(id) { NbInscritsProjection(0) }
    }
}
