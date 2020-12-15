import Commande.*
import Evenement.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DistributionInscriptionShould {

    val a = Distributeur("A")

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

        val actual = distributionInscription.executeCommande(InscrirePourLaDistribution(a))

        assertThat(actual).isEqualTo(DistributeurInscrit(a))
    }

    @Test
    fun `ne pas inscrire si l'inscription n'est pas démarrée`(){
        val distributionInscription = DistributionInscription()

        val actual = distributionInscription.executeCommande(InscrirePourLaDistribution(a))

        assertThat(actual).isNull()
    }

    @Test
    fun `desinscrire de la distribution`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(DemarrerInscription)
        distributionInscription.executeCommande(InscrirePourLaDistribution(a))

        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution(a))

        assertThat(actual).isEqualTo(DistributeurDesinscrit(a))
    }

    @Test
    fun `ne pas desinscrire si personne n'est inscrit`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(DemarrerInscription)

        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution(a))

        assertThat(actual).isNull()
    }

    @Test
    fun `ne pas desinscrire deux fois`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(DemarrerInscription)
        distributionInscription.executeCommande(InscrirePourLaDistribution(a))

        distributionInscription.executeCommande(DesinscrireDeLaDistribution(a))

        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution(a))

        assertThat(actual).isNull()
    }

    @Test
    fun `ne pas desinscrire si B n'est pas inscrit`(){
        val distributionInscription = DistributionInscription()
        distributionInscription.executeCommande(DemarrerInscription)
        // inscrire A.
        distributionInscription.executeCommande(InscrirePourLaDistribution(a))

        // désinscrire B
        val b = Distributeur("B")
        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution(b))

        assertThat(actual).isNull()
    }
}
