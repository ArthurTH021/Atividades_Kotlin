package sistema.caixadeagua

import enums.Cor
import enums.Material
import produto.CaixaDaAgua
import repositorio.CRUDCaixaDaAgua
import utils.Validacoes
import java.math.BigDecimal

fun cadastrarNovaCaixa() {
    println("\n--- CADASTRAR CAIXA D'ÁGUA ---")

    val marca = Validacoes.lerTextoObrigatorio("Digite a marca: ")
    val modelo = Validacoes.lerTextoObrigatorio("Digite o modelo: ")

    // Usando a leitura segura de Double para evitar quebra de sistema
    val largura = Validacoes.lerDouble("Digite a largura: ")
    val altura = Validacoes.lerDouble("Digite a altura: ")
    val profundidade = Validacoes.lerDouble("Digite a profundidade: ")

    // A dimensão é a combinação das 3 variáveis acima
    val dimensao = mutableListOf(largura, altura, profundidade)

    println("\nEscolha a cor: ")
    Cor.entries.forEach { corItem ->
        println("[${corItem.ordinal}] ${corItem.name}")
    }
    // Usando leitura segura de inteiro com fallback caso o índice não exista
    val corIndex = Validacoes.lerInteiro("Número da cor: ")
    val corEscolhida = Cor.entries.getOrElse(corIndex) { Cor.AZUL }
    println("\nEscolha o material: ")
    Material.entries.forEach { materialItem ->
        println("[${materialItem.ordinal}] ${materialItem.name}")
    }
    val materialIndex = Validacoes.lerInteiro("Número do material: ")
    val materialEscolhido = Material.entries.getOrElse(materialIndex) { Material.POLIETILENO }

    val formato = Validacoes.lerTextoObrigatorio("Escolha o formato: ")

    // Usando leitura segura para o preço convertendo para BigDecimal sem risco de crash
    val precoDouble = Validacoes.lerDouble("Qual é o preço: ")
    val preco = BigDecimal.valueOf(precoDouble)

    val conexao = CRUDCaixaDaAgua() // Cria a variável de conexão com o banco
    conexao.salvar( // Chama a função salvar
        CaixaDaAgua(
            marca = marca,
            material = materialEscolhido,
            modelo = modelo,
            dimensao = dimensao,
            cor = corEscolhida,
            formato = formato,
            preco = preco
        )
    )
    println("Caixa d'água cadastrada com sucesso!")
}