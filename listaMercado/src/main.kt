fun main() {

    val estoque = mutableMapOf<String, Double>()
    var opcao: Int

    do {
        println("\n============================================")
        println("Bem vindo! Escolha uma das opções abaixo")
        println("[1]Cadastrar produto")
        println("[2]Remover produto")
        println("[3]Alterar valor")
        println("[4]Mostrar lista")
        println("[5]Sair")
        println("============================================")
        print("Escolha uma das opções abaixo:")
        opcao = readln().toInt()

        when (opcao) {
            1 -> {
                println("\nNome do produto:")
                val nome = readln()
                println("Valor do produto:")
                val preco = readln().toDouble()

                estoque[nome] = preco
                println("Produto $nome cadastrado com sucesso!")
            }

            2 -> {
                println("Digite o produto que deseja remover")
                val nomeRemover = readln()
                estoque.remove(nomeRemover)
                println("Produto removido!")
            }

            3 -> {
                println("Digite o nome do produto para alterar o valor: ")
                val valor = readln()
                println("Digite o novo valor: ")
                val valorNovo = readln().toDouble()
                estoque[valor] = valorNovo
                println("O novo preço de $valor é $valorNovo")
            }

            4 -> {
                println("\n--- Lista de Produtos ---")
                for ((produto, preco) in estoque) {
                    println(" $produto - R$ $preco")
                }
            }
            5 -> println("Saindo...")
            else -> println("Opção inválida!")
        }

} while (opcao != 5)
}