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
    }

    fun demarrerInscription(): Evenement {
        return Evenement.InscriptionDemarree
    }

    fun inscrirePourLaDistribution(): Evenement? {
        return if (stream.contains(Evenement.InscriptionDemarree)) {
            Evenement.DistributeurInscrit
        } else null
    }


}
