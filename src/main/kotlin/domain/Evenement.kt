package domain

sealed class Evenement(open val id: Id) {
    data class InscriptionDemarree(override val id: Id) : Evenement(id)
    data class DistributeurInscrit(val distributeur: Distributeur, override val id: Id) : Evenement(id)
    data class DistributeurDesinscrit(val distributeur: Distributeur, override val id: Id) : Evenement(id)
}
