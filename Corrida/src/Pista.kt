data class Pista(
    val listaCarros: List<Carro>,
    val clima: String,
    val voltas: Int
) {

    fun simularCorrida() {

        val ordenados = listaCarros.sortedByDescending { carro ->
                    carro.velocidade +
                    carro.aceleracao +
                    carro.piloto.habilidade +
                    carro.freio +
                    carro.resistencia -
                    carro.consumo
        }

        println(" RESULTADO DA CORRIDA ")

        for (i in ordenados.indices) {
            val carro = ordenados[i]
            println("${i + 1}º lugar: ${carro.piloto.nome}")
        }
    }
}