package vendas

fun menuVenda() {
    do {
        println("\n============MENU VENDAS============")
        println("[0] Voltar")
        println("[1] Registrar Venda")
        println("===================================")
        print("Digite sua Opção: ")

        val op: Int = readln().toIntOrNull() ?: 10

        when (op) {
            1 -> {
                println("\n--- REGISTRAR VENDA ---")
                print("ID do produto: ")
                val idProd = readln().toIntOrNull() ?: 0

                print("Quantidade vendida: ")
                val qtd = readln().toIntOrNull() ?: 0

                print("Nome do cliente (Pagador): ")
                val pagador = readln()

                print("Quem recebeu (Recebedor): ")
                val recebedor = readln()

                print("Funcionário responsável: ")
                val responsavel = readln()

                registrarVenda(
                    idProduto = idProd,
                    quantidadeVendida = qtd,
                    pagador = pagador,
                    recebedor = recebedor,
                    responsavel = responsavel
                )
            }
            0 -> break
            else -> println("Opção inválida!")
        }
    } while (true)
}