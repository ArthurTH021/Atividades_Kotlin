package sistema

import sistema.caixadeagua.* //o asterisco puxa todos
import sistema.cliente.*
import sistema.funcionario.*
import pagamentos.*

fun menuInicial() {
    do {
        println("=====================================")
        println("[0] Sair")
        println("[1] Gerenciar Caixa de Água")
        println("[2] Gerenciar Cliente")
        println("[3] Gerenciar Funcionário")
        println("[4] Gerenciar Financeiro")
        println("=====================================")
        print("Digite sua Opção: ")

        val op : Int = readln().toIntOrNull() ?: 10

        when (op) {
            1 -> menuCaixaDeAgua()
            2 -> menuCliente()
            3 -> menuFuncionario()
            4 -> menuFluxoCaixa()
            0 -> {
                println("Adeus")
                break
            }
            else -> println("Opção inválida!")
        }
    } while (true)
}

fun menuCaixaDeAgua() {
    do {
        println("\n=== MENU CAIXA DE ÁGUA ===")
        println("[0] Voltar")
        println("[1] Cadastrar caixa de água")
        println("[2] Editar caixa de água")
        println("[3] Listar caixas de água")
        println("[4] Excluir caixa de água")
        println("=====================================")
        print("Digite sua Opção: ")

        val op : Int = readln().toIntOrNull() ?: 10

        when (op) {
            1 -> cadastrarNovaCaixa()
            2 -> editarCaixa()
            3 -> listarCaixa()
            4 -> excluirCaixa()
            0 -> break
            else -> println("Opção inválida!")
        }
    } while (true)
}

fun menuCliente() {
    do {
        println("\n=== MENU CLIENTE ===")
        println("[0] Voltar")
        println("[1] Cadastrar cliente")
        println("[2] Editar cliente")
        println("[3] Listar clientes")
        println("[4] Excluir cliente")
        println("=====================================")
        print("Digite sua Opção: ")

        val op : Int = readln().toIntOrNull() ?: 10

        when (op) {
            1 -> cadastrarCliente()
            2 -> editarCliente()
            3 -> listarCliente()
            4 -> excluirCliente()
            0 -> break
            else -> println("Opção inválida!")
        }
    } while (true)
}

fun menuFuncionario() {
    do {
        println("\n=== MENU FUNCIONÁRIO ===")
        println("[0] Voltar")
        println("[1] Cadastrar funcionario")
        println("[2] Editar funcionario")
        println("[3] Listar funcionarios")
        println("[4] Excluir funcionarios")
        println("=====================================")
        print("Digite sua Opção: ")

        val op : Int = readln().toIntOrNull() ?: 10

        when (op) {
            1 -> cadastrarFuncionario()
            2 -> editarFuncionario()
            3 -> listarFuncionario()
            4 -> excluirFuncionario()
            0 -> break
            else -> println("Opção inválida!")
        }
    } while (true)

    // Dentro do seu menu principal (ex: Main.kt)
    while (true) {
        println("\n=== SISTEMA DE GESTÃO ===")
        println("[1] Cadastrar Funcionário")
        println("[2] Cadastrar Cliente")
        println("[3] Cadastrar Caixa d'Água")
        println("[4] Fluxo de Caixa (Extrato / Registrar)") // <-- A opção nova
        println("[0] Sair")
        print("Escolha uma opção: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> cadastrarFuncionario()
            2 -> cadastrarCliente()
            3 -> cadastrarNovaCaixa()
            4 -> menuFluxoCaixa() // <-- Chamando o submenu financeiro
            0 -> {
                println("Saindo do sistema...")
                break
            }
            else -> println("Opção inválida!")
        }
    }
}