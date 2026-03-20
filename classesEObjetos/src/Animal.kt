// Classe mãe
open class Animal(
    val nome: String,
    val peso: Double
) {
    fun fazerBarulho() {
        println("$nome fez barulho!")
    }
}

// Classe filha
class Cachorro(nome: String, peso: Double) : Animal(nome, peso) {
    fun latir() {
        println("$nome latiu!")
    }
}

// Exemplo 2
class Gato : Animal(nome = "Mimo", peso = 2.0)

