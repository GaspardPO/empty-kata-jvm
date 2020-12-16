package infra

data class NbInscritsProjection(val compteur: Int = 0) {
    fun incrementCounter(): NbInscritsProjection {
        return copy(compteur = compteur + 1)
    }

    fun decrementCounter(): NbInscritsProjection {
        return copy(compteur = compteur - 1)
    }
}
