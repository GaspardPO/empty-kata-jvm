import Commande.*
import Evenement.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DistributionInscriptionShould {

    @Test
    fun `démarrer l'inscription`(){
        val distributionInscription = DistributionInscription()

        val actual = distributionInscription.executeCommande(DemarrerInscription)

        assertThat(actual).isEqualTo(InscriptionDemarree)
    }

    @Test
    fun `inscrire pour la distribution`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(DemarrerInscription)

        val actual = distributionInscription.executeCommande(InscrirePourLaDistribution)

        assertThat(actual).isEqualTo(DistributeurInscrit)
    }

    @Test
    fun `ne pas inscrire si l'inscription n'est pas démarrée`(){
        val distributionInscription = DistributionInscription()

        val actual = distributionInscription.executeCommande(InscrirePourLaDistribution)

        assertThat(actual).isNull()
    }

    @Test
    fun `desinscrire de la distribution`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(DemarrerInscription)
        distributionInscription.executeCommande(InscrirePourLaDistribution)

        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution)

        assertThat(actual).isEqualTo(DistributeurDesinscrit)
    }

    @Test
    fun `ne pas desinscrire si personne n'est inscrit`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(DemarrerInscription)

        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution)

        assertThat(actual).isNull()
    }

    @Test
    fun `ne pas desinscrire deux fois`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(DemarrerInscription)
        distributionInscription.executeCommande(InscrirePourLaDistribution)

        distributionInscription.executeCommande(DesinscrireDeLaDistribution)

        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution)

        assertThat(actual).isNull()
    }
}
