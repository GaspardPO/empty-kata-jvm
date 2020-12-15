import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DistributionInscriptionShould {

    @Test
    fun `démarrer l'inscription`(){
        // Given
        val distributionInscription = DistributionInscription()
        val commande : Commande = Commande.DemarreInscription

        // when
        val actual : Evenement = distributionInscription.executeCommande(commande)

        // then
        val expected : Evenement = Evenement.InscriptionDemarree
        assertThat(expected).isEqualTo(actual)
    }
}