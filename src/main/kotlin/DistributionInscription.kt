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
        is Commande.InscrirePourLaDistribution -> inscrirePourLaDistribution(commande)
        is Commande.DesinscrireDeLaDistribution -> desinscrire(commande)
    }

    private fun demarrerInscription(): Evenement {
        return Evenement.InscriptionDemarree
    }

    private fun inscrirePourLaDistribution(commande: Commande.InscrirePourLaDistribution): Evenement? {
        return if (stream.contains(Evenement.InscriptionDemarree)) {
            Evenement.DistributeurInscrit(commande.distributeur)
        } else null
    }

    private fun desinscrire(commande: Commande.DesinscrireDeLaDistribution): Evenement? {
        return if (stream.isNotEmpty()
            && stream.last().equals(Evenement.DistributeurInscrit(commande.distributeur))) {
            Evenement.DistributeurDesinscrit(commande.distributeur)
        } else null
    }
}
