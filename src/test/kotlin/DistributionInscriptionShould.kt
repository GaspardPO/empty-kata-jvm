import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DistributionInscriptionShould {

    @Test
    fun `démarrer l'inscription`(){
        val distributionInscription = DistributionInscription()

        val commande : Commande = Commande.DemarrerInscription
        val actual : Evenement? = distributionInscription.executeCommande(commande)

        val expected : Evenement = Evenement.InscriptionDemarree
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `inscrire pour la distribution`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(Commande.DemarrerInscription)

        val commande : Commande = Commande.InscrirePourLaDistribution
        val actual : Evenement? = distributionInscription.executeCommande(commande)

        val expected : Evenement = Evenement.DistributeurInscrit
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `ne pas inscrire si l'inscription n'est pas démarrée`(){
        val distributionInscription = DistributionInscription()

        val commande : Commande = Commande.InscrirePourLaDistribution
        val actual : Evenement? = distributionInscription.executeCommande(commande)

        val expected : Evenement? = null
        assertThat(expected).isEqualTo(actual)
    }
}