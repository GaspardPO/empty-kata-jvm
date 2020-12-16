package domain

import domain.Evenement.InscriptionDemarree

class DistributionInscription(val id : Id) {
    private val stream = mutableListOf<Evenement>()

    fun executeCommande(commande: Commande): Evenement? {
        val evenement = getEvenement(commande)
        if (evenement != null) {
            stream.add(evenement)
        }
        return evenement
    }

    private fun getEvenement(commande: Commande) = when (commande) {
        is Commande.DemarrerInscription -> demarrerInscription()
        is Commande.InscrirePourLaDistribution -> inscrirePourLaDistribution(commande)
        is Commande.DesinscrireDeLaDistribution -> desinscrire(commande)
    }

    private fun demarrerInscription(): Evenement {
        return InscriptionDemarree(id)
    }

    private fun inscrirePourLaDistribution(commande: Commande.InscrirePourLaDistribution): Evenement? {
        return if (stream.contains(InscriptionDemarree(id))) {
            Evenement.DistributeurInscrit(commande.distributeur, id)
        } else null
    }

    private fun desinscrire(commande: Commande.DesinscrireDeLaDistribution): Evenement? {

        val dernierEventInscrit  = stream.lastIndexOf(Evenement.DistributeurInscrit(commande.distributeur, id))
        val dernierEventDesinscrit  = stream.lastIndexOf(Evenement.DistributeurDesinscrit(commande.distributeur, id))
        return if (dernierEventInscrit > dernierEventDesinscrit) {
            Evenement.DistributeurDesinscrit(commande.distributeur, id)
        } else null
    }
}

data class Id(val int: Int)
