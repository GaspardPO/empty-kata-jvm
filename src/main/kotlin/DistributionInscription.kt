class DistributionInscription {
    val stream = mutableListOf<Evenement>()

    fun executeCommande(commande: Commande): Evenement? {
        val evenement = getEvenement(commande)
        if (evenement != null) {
            stream.add(evenement)
        }
        return evenement
    }

    private fun getEvenement(commande: Commande) = when (commande) {
        is Commande.DemarrerInscription -> demarrerInscription()
        is Commande.InscrirePourLaDistribution -> inscrirePourLaDistribution()
        is Commande.DesinscrireDeLaDistribution -> desinscrire()
    }

    private fun demarrerInscription(): Evenement {
        return Evenement.InscriptionDemarree
    }

    private fun inscrirePourLaDistribution(): Evenement? {
        return if (stream.contains(Evenement.InscriptionDemarree)) {
            Evenement.DistributeurInscrit
        } else null
    }

    private fun desinscrire(): Evenement? {
        return if (stream.contains(Evenement.DistributeurInscrit)) {
            Evenement.DistributeurDesinscrit
        } else null
    }


}
