package domain

sealed class Commande {
    object DemarrerInscription : Commande()
    data class InscrirePourLaDistribution(val distributeur : Distributeur) : Commande()
    data class DesinscrireDeLaDistribution(val distributeur : Distributeur) : Commande()
}
