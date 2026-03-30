fun main() {

        val piloto1 = Piloto("João", 25, 6.0)
        val piloto2 = Piloto("Lucas", 22, 8.0)
        val piloto3 = Piloto("Pedro", 19, 10.0)

        val carro1 = Carro(275.0, 5.5, 7.0, 7.0, 6.0, 8.0, piloto1)
        val carro2 = Carro(290.0, 3.2, 10.0, 6.5, 9.0, 7.0, piloto2)
        val carro3 = Carro(280.0, 9.6, 3.0, 10.0, 10.0, 5.0, piloto3)

        val carros = listOf(carro1, carro2, carro3)

        val pista = Pista(carros, "Sol", 10)

        pista.simularCorrida()
}