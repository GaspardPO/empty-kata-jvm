sealed class Evenement {
    object InscriptionDemarree : Evenement()
    data class DistributeurInscrit(val distributeur: Distributeur) : Evenement()
    data class DistributeurDesinscrit(val distributeur: Distributeur) : Evenement() {

    }
}
