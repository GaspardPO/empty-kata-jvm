package domain

import domain.Commande.*
import domain.Evenement.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DistributionInscriptionShould {

    val a = Distributeur("A")
    val b = Distributeur("B")


    private val id = Id(0)

    @Test
    fun `démarrer l'inscription`(){
        val distributionInscription = DistributionInscription(emptyList())

        val actual = distributionInscription.executeCommande(DemarrerInscription)

        assertThat(actual).isEqualTo(InscriptionDemarree(id))
    }

    @Test
    fun `inscrire pour la distribution`(){
        val distributionInscription = DistributionInscription(listOf(InscriptionDemarree(id)))

        val actual = distributionInscription.executeCommande(InscrirePourLaDistribution(a))

        assertThat(actual).isEqualTo(DistributeurInscrit(a, id))
    }

    @Test
    fun `ne pas inscrire si l'inscription n'est pas démarrée`(){
        val distributionInscription = DistributionInscription(emptyList())

        val actual = distributionInscription.executeCommande(InscrirePourLaDistribution(a))

        assertThat(actual).isNull()
    }

    @Test
    fun `desinscrire de la distribution`(){
        val distributionInscription = DistributionInscription(listOf(InscriptionDemarree(id), DistributeurInscrit(a, id)))

        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution(a))

        assertThat(actual).isEqualTo(DistributeurDesinscrit(a, id))
    }

    @Test
    fun `ne pas desinscrire si personne n'est inscrit`(){
        val distributionInscription = DistributionInscription(listOf(InscriptionDemarree(id)))

        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution(a))

        assertThat(actual).isNull()
    }

    @Test
    fun `ne pas desinscrire deux fois`(){
        val distributionInscription = DistributionInscription(listOf(InscriptionDemarree(id), DistributeurInscrit(a, id)))

        distributionInscription.executeCommande(DesinscrireDeLaDistribution(a))

        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution(a))

        assertThat(actual).isNull()
    }

    @Test
    fun `ne pas desinscrire si B n'est pas inscrit`(){
        val distributionInscription = DistributionInscription(listOf(InscriptionDemarree(id), DistributeurInscrit(a, id)))

        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution(b))

        assertThat(actual).isNull()
    }

    @Test
    fun `desinscrire A de la distribution quand B est le dernier inscrit`(){
        val distributionInscription = DistributionInscription(listOf(InscriptionDemarree(id), DistributeurInscrit(a, id), DistributeurInscrit(b, id)))

        val actual = distributionInscription.executeCommande(DesinscrireDeLaDistribution(a))

        assertThat(actual).isEqualTo(DistributeurDesinscrit(a, id))
    }
    
}
