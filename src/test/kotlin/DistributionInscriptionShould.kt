import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DistributionInscriptionShould {

    @Test
    fun `démarrer l'inscription`(){
        val distributionInscription = DistributionInscription()

        val commande : Commande = Commande.DemarrerInscription
        val actual : Evenement? = distributionInscription.executeCommande(commande)

        val expected : Evenement = Evenement.InscriptionDemarree
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `inscrire pour la distribution`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(Commande.DemarrerInscription)

        val commande : Commande = Commande.InscrirePourLaDistribution
        val actual : Evenement? = distributionInscription.executeCommande(commande)

        val expected : Evenement = Evenement.DistributeurInscrit
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `ne pas inscrire si l'inscription n'est pas démarrée`(){
        val distributionInscription = DistributionInscription()

        val commande : Commande = Commande.InscrirePourLaDistribution
        val actual : Evenement? = distributionInscription.executeCommande(commande)

        val expected : Evenement? = null
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `desinscrire de la distribution`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(Commande.DemarrerInscription)
        distributionInscription.executeCommande(Commande.InscrirePourLaDistribution)

        val commande : Commande = Commande.DesinscrireDeLaDistribution
        val actual : Evenement? = distributionInscription.executeCommande(commande)


        val expected : Evenement = Evenement.DistributeurDesinscrit
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `ne pas desinscrire si personne n'est inscrit`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(Commande.DemarrerInscription)

        val commande : Commande = Commande.DesinscrireDeLaDistribution
        val actual : Evenement? = distributionInscription.executeCommande(commande)

        val expected : Evenement? = null
        assertThat(actual).isEqualTo(expected)
    }
}