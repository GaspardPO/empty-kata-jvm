package domain

sealed class Evenement(open val idDistributionInscription: Id) {
    data class InscriptionDemarree(override val idDistributionInscription: Id) : Evenement(idDistributionInscription)
    data class DistributeurInscrit(val distributeur: Distributeur, override val idDistributionInscription: Id) : Evenement(idDistributionInscription)
    data class DistributeurDesinscrit(val distributeur: Distributeur, override val idDistributionInscription: Id) : Evenement(idDistributionInscription)
}
