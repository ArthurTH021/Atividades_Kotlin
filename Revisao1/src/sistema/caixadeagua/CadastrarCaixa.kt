package sistema.caixadeagua

import enums.Cor
import enums.Habilidade
import enums.Material
import enums.Turno
import pessoas.Instalador
import java.math.BigDecimal

fun cadastrarNovaCaixa(){
    println("Digite a marca: ")
    val marca = readln()

    println("Digite o modelo: ")
    val modelo = readln()

    println("Digite a largura: ")
    val largura = readln().toDouble()
    println("Digite a altura: ")
    val altura = readln().toDouble()
    println("Digite a profundidade: ")
    val profundidade = readln().toDouble()
    //A dimensão é a combinação das 3 variaveis acima
    val dimensao = listOf<Double>(largura, altura, profundidade)

    println("Escolha a cor: ")
    Cor.entries.forEach { cor ->
        println("[${cor.ordinal}] ${cor.name}")
    }
    println("Número da cor: ")
    val cor = readln().toInt()

    println("Escolha a o material: ")
    Material.entries.forEach { material ->
        println("[${material.ordinal}] ${material.name}")
    }
    println("Número do material: ")
    val material = readln().toInt()

    println("Escolha o formato: ")
    val formato = readln()

    println("Qual é o preço: ")
    val preco = readln()
}
