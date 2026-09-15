package sistema

import sistema.caixadeagua.*
import sistema.cliente.*
import sistema.funcionario.*
import sistema.pagamentos.*
import repositorio.CRUDServico
import produto.Servico
import java.math.BigDecimal

fun menuInicial() {
    do {
        println("===========MENU INICIAL==============")
        println("[0] Sair")
        println("[1] Gerenciar Caixa de Água")
        println("[2] Gerenciar Cliente")
        println("[3] Gerenciar Funcionário")
        println("[4] Gerenciar Financeiro")
        println("[5] Gerenciar Serviço") // <-- Opção nova do serviço
        println("=====================================")
        print("Digite sua Opção: ")

        val op : Int = readln().toIntOrNull() ?: 10

        when (op) {
            1 -> menuCaixaDeAgua()
            2 -> menuCliente()
            3 -> menuFuncionario()
            4 -> menuFluxoCaixa()
            5 -> menuServico() // <-- Chamando o menu de serviços
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
        println("\n=========MENU CAIXA DE AGUA===========")
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
        println("\n============MENU CLIENTE=============")
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
        println("\n==========MENU FUNCIONÁRIO===========")
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
}

fun menuServico() {
    val crudServico = CRUDServico()

    do {
        println("\n============MENU SERVIÇO=============")
        println("[0] Voltar")
        println("[1] Cadastrar serviço")
        println("[2] Listar serviços")
        println("=====================================")
        print("Digite sua Opção: ")

        val op: Int = readln().toIntOrNull() ?: 10

        when (op) {
            1 -> {
                print("Digite o nome do serviço: ")
                val nome = readln()
                print("Digite a descrição: ")
                val descricao = readln()
                print("Digite o preço (ex: 150.00): ")
                val precoInput = readln().toDoubleOrNull() ?: 0.0
                val preco = BigDecimal.valueOf(precoInput)

                val novoServico = Servico(
                    nome = nome,
                    descricao = descricao,
                    preco = preco
                )
                crudServico.cadastrar(novoServico)
            }
            2 -> {
                crudServico.listar()
            }
            0 -> break
            else -> println("Opção inválida!")
        }
    } while (true)
}